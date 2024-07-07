package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task findTaskByName(String name) {
        for (Task task : tasks) {
            Task found = findTaskByNameRecursive(task, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private Task findTaskByNameRecursive(Task current, String name) {
        if (current.getName().equals(name)) {
            return current;
        }
        if (current.getSubtasks() != null) {
            for (Task subTask : current.getSubtasks()) {
                Task found = findTaskByNameRecursive(subTask, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }


    public void displayTaskDetails(Task task) {
        System.out.println("ID: " + task.getId());
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
        System.out.println("Gems: " + task.getGems().getAmount());
        System.out.println("Skills: ");
        if(!task.getSkills().isEmpty())for (int i = 0; i<task.getSkills().size();i++) System.out.println("-"+task.getSkills().get(i).getName());
        else System.out.println("No Skills Selected");
        Task parent = findParentTask(task);
        if (parent != null) {
            System.out.println("Parent:");
            printTask(parent, "", true);
        }
    }
    public Task findParentTask(Task target) {
        for (Task task : tasks) {
            Task parent = findParentTaskRecursive(task, target);
            if (parent != null) {
                return parent;
            }
        }
        return null;
    }

    private Task findParentTaskRecursive(Task current, Task target) {
        if (current.getSubtasks() != null) {
            for (Task subTask : current.getSubtasks()) {
                if (subTask.equals(target)) {
                    return current;
                }
                Task parent = findParentTaskRecursive(subTask, target);
                if (parent != null) {
                    return parent;
                }
            }
        }
        return null;
    }


    private int getMaxSubtaskId(Task task) {
        int maxId = 0;
        if (task.getSubtasks() != null) {
            for (Task subTask : task.getSubtasks()) {
                maxId = Math.max(maxId, subTask.getId());
                int subTaskMaxId = getMaxSubtaskId(subTask);
                maxId = Math.max(maxId, subTaskMaxId);
            }
        }
        return maxId;
    }

    // New method to display all tasks and subtasks as a tree
    public void displayAllTasksAsTree() {
        System.out.println("Task Tree:");
        for (Task task : tasks) {
            printTask(task, "", true);
        }
    }

    private void printTask(Task task, String indent, boolean last) {
        System.out.print(indent);
        if (last) {
            System.out.print("└─ ");
            indent += "   ";
        } else {
            System.out.print("├─ ");
            indent += "│  ";
        }
        System.out.println(task.getName());

        if (task.getSubtasks() != null) {
            List<Task> subtasks = task.getSubtasks();
            for (int i = 0; i < subtasks.size(); i++) {
                printTask(subtasks.get(i), indent, i == subtasks.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        // Add tasks and subtasks to taskList

        taskList.displayAllTasksAsTree();
    }
}
//todo function to view subtask if current tasked being viewed has no parent
