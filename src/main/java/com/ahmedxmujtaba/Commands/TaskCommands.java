package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.*;
import com.ahmedxmujtaba.Backend.Utility.ClockTime;
import com.ahmedxmujtaba.Backend.Utility.Date;
import com.ahmedxmujtaba.Backend.Utility.DurationTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskCommands implements Command {
    private TaskList tasks;
    private SkillTree skillTree;

    public TaskCommands(SkillTree skillTree, TaskList tasks) {
        this.tasks = tasks;
        this.skillTree = skillTree;
    }

    @Override
    public int execute(String[] arguments) {
        if (arguments.length > 0) {
            String subCommand = arguments[0];
            switch (subCommand) {
                case "-add", "-a":
                    return addTaskWithConsoleInput() ? 2:-1;
                case "-addSimple", "-as":
                    return addSimpleTask(arguments) ? 2:-1;
                case "-edit", "-e":
                    return editTask(arguments) ? 2:-1;
                case "-delete", "-del":
                    return deleteTask(arguments) ? 2:-1;
                case "-view", "-v", "-d", "-display":
                    return viewTask(arguments) ? 2:-1;
                case "-help", "-h":
                    displayHelp();
                    return -1;
                default:
                    System.out.println("Invalid Command: Task -[field] [options] \n-help to see options");
                    return -1;
            }
        } else {
            System.out.println("Usage: Task <field> [options]");
            return -1;
        }
    }

    private boolean addSimpleTask(String[] args) {
        try {
            String name = args[1];
            String description = args[2];
            double expPoints = Double.parseDouble(args[3]);
            Difficulty difficulty = validateDifficulty(args[4]);
            Priority priority = validatePriority(args[5]);
            LocalDate dueDate = Date.parseDate(args[6]);
            LocalTime timeDue = ClockTime.parseStringToLocalTime12Hour(args[7]);
            LocalDate dateCreated = Date.currentDate();
            DurationTime duration = new DurationTime(Integer.parseInt(args[8]), Integer.parseInt(args[9]));
            ArrayList<Skill> skills = validateSkills(args[10]);
            boolean completionStatus = Boolean.parseBoolean(args[11]);
            Gems reward = new Gems(Integer.parseInt(args[12]));

            Task newTask = new Task(name, description, difficulty, expPoints, priority, dueDate, timeDue, dateCreated, duration, skills, completionStatus, null, reward);
            tasks.getTasks().add(newTask);
            System.out.println("Task added: " + newTask.getName());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to add task. Please check your input.");
            return false;
        }
    }

    private boolean addTaskWithConsoleInput() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Task Name: ");
            String name = input.nextLine();
            System.out.println("Task Description: ");
            String description = input.nextLine();
            System.out.println("Task Difficulty (NONE, LOW, MEDIUM, HIGH): ");
            String difficulty = input.nextLine();
            Difficulty difficultyEnum = validateDifficulty(difficulty);
            System.out.println("EXP Gained: ");
            double exp = input.nextDouble();
            input.nextLine(); // Consume newline character
            System.out.println("Task Priority (NONE, LOW, MEDIUM, HIGH): ");
            String priority = input.nextLine();
            Priority priorityEnum = validatePriority(priority);
            System.out.println("Due Date (dd/mm/yyyy): ");
            String due = input.nextLine();
            LocalDate dueDate = validateDate(due);
            LocalDate creationDate = Date.currentDate();
            System.out.println("Task Time due (hh:mm AM/PM): ");
            String timeDueStr = input.nextLine();
            LocalTime timeDue = validateTime(timeDueStr);
            DurationTime duration = validateDuration(input);
            System.out.println("Skill(s) separated by comma ',' : ");
            String skillStr = input.nextLine();
            ArrayList<Skill> skills = validateSkills(skillStr);
            System.out.println("Parent Task Name: (Blank for No parent)");
            String parentName = input.nextLine();
            Task parentTask = getParent(parentName);
            Gems gems = validateReward(input);

            Task newTask = new Task(name, description, difficultyEnum, exp, priorityEnum, dueDate, timeDue, creationDate, duration, skills, false,null, gems);
            if (parentTask != null) {
                parentTask.addSubtask(newTask);
            }
            tasks.getTasks().add(newTask);
            System.out.println("Task added: " + newTask.getName());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to add task. Please check your input.");
            return false;
        }
    }

    private boolean editTask(String[] arguments) {
        try {
            int id = Integer.parseInt(arguments[1]);
            Task task = findTaskById(id);
            if (task != null) {
                String paramToChange = arguments[2];
                switch (paramToChange) {
                    case "name":
                        task.setName(arguments[3]);
                        break;
                    case "description":
                        task.setDescription(arguments[3]);
                        break;
                    // Add more cases as needed
                    default:
                        System.out.println("Invalid field to edit. \n-help to see options");
                        return false;
                }
                System.out.println("Task updated: " + task.getName());
                return true;
            } else {
                System.out.println("Task not found with ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to edit task. Please check your input.");
            return false;
        }
    }

    private boolean deleteTask(String[] arguments) {
        try {
            int id = Integer.parseInt(arguments[1]);
            Task task = findTaskById(id);
            if (task != null) {
                tasks.getTasks().remove(task);
                System.out.println("Task deleted: " + task.getName());
                return true;
            } else {
                System.out.println("Task not found with ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to delete task. Please check your input.");
            return false;
        }
    }

    private boolean viewTask(String[] arguments) {
        try {
            int id = Integer.parseInt(arguments[1]);
            Task task = findTaskById(id);
            if (task != null) {
                displayTask(task);
                return false; // No need to save any data since none is updated
            } else {
                System.out.println("Task not found with ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to view task. Please check your input.");
            return false;
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks.getTasks()) {
            Task found = findTaskByIdRecursive(task, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private Task findTaskByIdRecursive(Task current, int id) {
        if (current.getId() == id) {
            return current;
        }
        if (current.getSubtasks() != null) {
            for (Task subTask : current.getSubtasks()) {
                Task found = findTaskByIdRecursive(subTask, id);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private void displayTask(Task task) {
        System.out.println("Name: " + task.getName());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Difficulty: " + task.getDifficulty());
        System.out.println("Priority: " + task.getPriority());
        System.out.println("Exp Points: " + task.getExpPoints());
        System.out.println("Due Date: " + task.getDueDate());
        System.out.println("Time Due: " + task.getTimeDue());
        System.out.println("Date Created: " + task.getDateCreated());
        System.out.println("Duration: " + task.getDuration());
        System.out.println("Completion Status: " + task.getCompletionStatus());
        System.out.println("Gems: " + task.getReward());
        // Display subtasks and skills if needed
    }

    private void displayHelp() {
        System.out.println("Task Commands:" +
                "\n-add, -a <name> <description> <difficulty> <expPoints> <priority> <dueDate> <timeDue> <duration> <skills> <completionStatus> <reward>: Add a new task\n" +
                "-addSimple, -as <name> <description> <expPoints> <difficulty> <priority> <dueDate> <timeDue> <duration> <skills> <completionStatus> <reward>: Add a simple task\n" +
                "-edit, -e <id> <field> <value> : Edit task fields\n" +
                "-delete, -del <id> : Delete a task\n" +
                "-view, -v <id> : View a task\n" +
                "-help, -h : Display this help message\n" +
                "Fields to edit: \nname \ndescription \ndifficulty \npriority \ndueDate \ntimeDue \nduration \nskills \ncompletionStatus \nreward");
    }

    private Difficulty validateDifficulty(String difficulty) {
        difficulty = difficulty.toUpperCase();
        switch (difficulty) {
            case "NONE":
                return Difficulty.NONE;
            case "LOW":
                return Difficulty.LOW;
            case "MEDIUM":
                return Difficulty.MEDIUM;
            case "HIGH":
                return Difficulty.HIGH;
            default:
                System.out.println("Unknown Difficulty set to NONE");
                return Difficulty.NONE;
        }
    }

    private Priority validatePriority(String priority) {
        priority = priority.toUpperCase();
        switch (priority) {
            case "NONE":
                return Priority.NONE;
            case "LOW":
                return Priority.LOW;
            case "MEDIUM":
                return Priority.MEDIUM;
            case "HIGH":
                return Priority.HIGH;
            default:
                System.out.println("Unknown Priority set to NONE");
                return Priority.NONE;
        }
    }

    private LocalDate validateDate(String date) {
        return Date.parseDate(date);
    }

    private LocalTime validateTime(String time) {
        LocalTime localTime = ClockTime.parseStringToLocalTime12Hour(time);
        return localTime;
    }

    private DurationTime validateDuration(Scanner input) {
        int count = 0;
        while (count < 2) {
            try {
                System.out.println("Enter hours: ");
                int hours = input.nextInt();
                System.out.println("Enter minutes: ");
                int minutes = input.nextInt();
                return new DurationTime(hours, minutes);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid hours and minutes.");
                input.nextLine(); // Consume invalid input
                count++;
            }
        }
        System.out.println("Duration set to zero");
        return new DurationTime(0, 0);
    }

    private ArrayList<Skill> validateSkills(String input) {
        String[] skillsArr = input.split(",");
        ArrayList<Skill> skillsList = new ArrayList<>();
        if (input.isBlank()) {
            System.out.println("No Skills");
            return skillsList;
        }
        for (int i = 0; i < skillsArr.length; i++) {
            Skill currSkill = skillTree.findSkillByName(skillsArr[i]);
            if (currSkill != null) {
                skillsList.add(currSkill);
            } else {
                System.out.println("Skill: " + skillsArr[i] + " not found.");
            }
        }
        return skillsList;
    }

    private Task getParent(String name) {
        if (name.isBlank()) {
            return null;
        }
        Task foundTask = tasks.findTaskByName(name);
        if (foundTask == null) {
            System.out.println("Parent Task " + name + " not found.");
        }
        return foundTask;
    }

    private Gems validateReward(Scanner input) {
        int tries = 0;
        while (tries < 2) {
            try {
                System.out.println("Gems: ");
                int quantity = input.nextInt();
                return new Gems(quantity);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid quantity.");
                input.nextLine(); // Consume invalid input
                tries++;
            }
        }
        System.out.println("Gems set to 0");
        return new Gems(0);
    }
}
