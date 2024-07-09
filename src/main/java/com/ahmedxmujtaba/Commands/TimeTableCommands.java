package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.*;
import com.ahmedxmujtaba.Backend.Utility.ClockTime;
import com.ahmedxmujtaba.Backend.Utility.Date;
import com.ahmedxmujtaba.Backend.Utility.DurationTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeTableCommands implements Command {
    private SkillTree skillTree;
    private Profile profile;
    private RepetitiveTaskList tasks;

    public TimeTableCommands(SkillTree skillTree, RepetitiveTaskList tasks, Profile profile) {
        this.skillTree = skillTree;
        this.profile = profile;
        this.tasks = tasks;
    }

    @Override
    public int execute(String[] arguments) {
        if (arguments.length > 0) {
            String subCommand = arguments[0];
            switch (subCommand) {
                case "-tt","-timetable":
                    displayRepetitveTasks();
                    return -1;
                case "-add", "-a":
                    return addRepetitiveTask() ? 4:-1;
                case "-check","-c","-complete":
                    return checkTask(arguments) ? 4:-1;
                case "-incomplete", "-in","-uncheck","-un":
                    return uncheckTask(arguments) ? 4:-1;
                case "-edit", "-e":
                    return editTask(arguments) ? 4:-1;
                case "-delete", "-del":
                    return deleteTask(arguments) ? 4:-1;
                case "-view", "-v", "-d", "-display":
                    return viewTask(arguments) ? 4:-1;
                case "-help", "-h":
                    displayHelp();
                    return -1;
                default:
                    System.out.println("Invalid Command: TimeTable -[field] [options] \n-help to see options");
                    return -1;
            }
        } else {
            System.out.println("Usage: TimeTable <field> [options]");
            return -1;
        }
    }
    private void displayRepetitveTasks() {
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            tasks.displayFormatedTimeTabelUsingRepetitiveTasks();
        }
    }

    private boolean addRepetitiveTask() {
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
            LocalDate creationDate = Date.currentDate();
            System.out.println("Task Time due (hh:mm AM/PM): ");
            String timeDueStr = input.nextLine();
            LocalTime timeDue = validateTime(timeDueStr);
            System.out.println("Enter duration [hour]h [min]m e.g 1h 10m: ");
            String durationStr = input.nextLine();
            DurationTime duration = validateDuration(durationStr);
            input.nextLine();
            System.out.println("Skill(s) separated by comma ',' : ");
            String skillStr = input.nextLine();
            ArrayList<Skill> skills = validateSkills(skillStr);;
            System.out.println("Gems: ");
            String gemsStr = input.nextLine();
            Gems gems = validateReward(gemsStr);


            RepetitiveTask newTask = new RepetitiveTask(name, description, difficultyEnum, exp, priorityEnum, timeDue, creationDate, duration, skills,false, gems);

            tasks.getTasks().add(newTask);
            System.out.println("Task added: " + newTask.getName());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to add task. Please check your input.");
            return false;
        }
    }

    private boolean checkTask(String[] arguments)
    {
        if (arguments.length > 1){
            String taskName = arguments[1];
            RepetitiveTask task  = tasks.findTaskByName(taskName);
            if (task == null) {
                System.out.println("Task: " + taskName + " Not found 404");
                return false;
            }
            if (task.getCompletionStatus())
                return false;
            else if (TaskCompletion.taskDone(task,profile,skillTree))
            {
                task.setCompletionStatus(true);
                System.out.println("Task:" + taskName + " Complete!");

                return true;
            }
        }
        System.out.println("Unable to Mark status to done, check input");
        return false;
    }
    private boolean uncheckTask(String[] arguments){
        if (arguments.length > 1){
            String taskName = arguments[1];
            RepetitiveTask task  = tasks.findTaskByName(taskName);
            if (task == null) {
                System.out.println("Task: " + taskName + " Not found 404");
                return false;
            }
            if(task.getCompletionStatus() == false)
                return false;
            else
            {
                System.out.println("Task:" + taskName + " marked as uncompleted");
                task.setCompletionStatus(false);
                TaskCompletion.taskUndone(task,profile);
                return true;
            }
        }
        System.out.println("Unable to Mark status to false, check input");
        return false;
    }


    private double validateExp(String expStr) {
        double exp;
        try {
            exp = Double.parseDouble(expStr);
            if (exp<0) {
                System.out.println("Exp set to 0, cannot be negative");
                return 0;
            }
            return exp;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid arguments set to 0");
            return 0;
        }
    }

    private boolean editTask(String[] arguments) {
        try {
            String name = arguments[1];
            RepetitiveTask task = tasks.findTaskByName(name);
            //check to make sure a completed task cannot be edited
            if (task != null) {
                if (task.getCompletionStatus()) {
                    System.out.println("Task Already Completed!");
                    return false;
                }
                String paramToChange = arguments[2];
                switch (paramToChange) {
                    case "name":
                        task.setName(arguments[3]);
                        break;
                    case "description":
                        StringBuilder stringBuilder = new StringBuilder();
                        String newValue;
                        for (int i = 3; i < arguments.length; i++) {
                            stringBuilder.append(arguments[i]);
                            stringBuilder.append(" ");
                        }
                        newValue = stringBuilder.toString();
                        task.setDescription(newValue);
                        break;
                    case "priority":
                        task.setPriority(validatePriority(arguments[3]));
                        break;
                    case "difficulty":
                        task.setDifficulty(validateDifficulty(arguments[3]));
                        break;
                    case "timeDue":
                        StringBuilder stringBuilder2 = new StringBuilder();
                        for (int i = 3; i < arguments.length; i++) {
                            stringBuilder2.append(arguments[i]);
                            stringBuilder2.append(" ");
                        }
                        task.setTimeDue(validateTime(stringBuilder2.toString().trim()));
                        break;
                    case "skills":
                        task.setSkills(validateSkills(arguments[3]));
                        break;
                    case "gems":
                        task.setGems(validateReward(arguments[3]));
                        break;
                    case "duration":
                        StringBuilder stringBuilder1 = new StringBuilder();
                        for (int i = 3; i < arguments.length; i++) {
                            stringBuilder1.append(arguments[i]);
                            stringBuilder1.append(" ");
                        }
                        task.setDuration(validateDuration(stringBuilder1.toString().trim()));
                        break;
                    case "exp":
                        task.setExpPoints(validateExp(arguments[3]));
                        break;
                    default:
                        System.out.println("Invalid field to edit. \n-help to see options");
                        return false;
                }


                System.out.println("Task updated: " + task.getName());
                return true;
            } else {
                System.out.println("Task not found with Name: " + name);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to edit task. Please check your input.");
            return false;
        }
    }
    private boolean deleteTask(String[] arguments) {
        try {
            String name = buildString(arguments,1);
            RepetitiveTask task = tasks.findTaskByName(name);
            if (task != null) {
                tasks.getTasks().remove(task);
                System.out.println("Task deleted: " + task.getName());
                return true;
            } else {
                System.out.println("Task not found with Name: " + name);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to delete task. Please check your input.");
            return false;
        }
    }

    private String buildString(String arguments[],int startArg){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startArg; i < arguments.length; i++) {
            stringBuilder.append(arguments[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
    private boolean viewTask(String[] arguments) {
        try {
            String newValue = buildString(arguments,1);
            String name = newValue;
            RepetitiveTask task = tasks.findTaskByName(name);
            if (task != null) {
                tasks.displayTaskDetails(task);
                return false; // No need to save any data since none is updated
            } else {
                System.out.println("Task not found with name: " + name);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to view task. Please check your input.");
            return false;
        }
    }


    private void displayHelp() {
        System.out.println("Task Commands:" +
                "\n-add, -a : Add a task in daily list\n" +
                "-edit, -e <name> <field> <value> : Edit task fields\n" +
                "-delete, -del <name> : Delete a task\n" +
                "-view, -v, -display, -d <name> : View a task\n" +
                "-timetable, -tt : Display all daily tasks or time table\n"+
                "-help, -h : Display this help message\n" +
                "Fields to edit: \nname \ndescription \ndifficulty \npriority \ntimeDue \nduration \nskills \nexp \ngems");
    }

    private Difficulty validateDifficulty(String difficulty) {
        if(difficulty.isBlank()) {
            System.out.println("Priority set to NONE");
            return Difficulty.NONE;
        }
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
        if(priority.isBlank()) {
            System.out.println("Priority set to NONE");
            return Priority.NONE;
        }
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


    private LocalTime validateTime(String time) {
        LocalTime localTime = ClockTime.parseStringToLocalTime12Hour(time);
        return localTime;
    }

    private DurationTime validateDuration(String args) {
        if (args.isBlank()) {
            System.out.println("Time set to 0");
            return new DurationTime(0, 0);
        }
        DurationTime durationTime;
        try
        {
            durationTime = new DurationTime(args);
        }
        catch (Exception e) {
            System.out.println("Invalid input. Duration set to 0");
            return new DurationTime(0, 0);

        }
        return durationTime;
    }

    private ArrayList<Skill> validateSkills(String input) {
        String[] skillsArr = input.split(",");
        ArrayList<Skill> skillsList = new ArrayList<>();
        if (input.isBlank()) {
            System.out.println("No Skill Selected");
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


    private Gems validateReward(String input ) {
        if (input.isBlank()) System.out.println("Gems set to 0");
        try {
            System.out.println("Gems: ");
            int quantity = Integer.parseInt(input);
            return new Gems(quantity);
        } catch (Exception e) {
            System.out.println("Invalid input. Gems set to 0");
        }
        return new Gems(0);
    }
}
