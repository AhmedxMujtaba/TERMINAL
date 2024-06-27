package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;
import java.util.ArrayList;

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

    public boolean deleteTask(int id) {
        Task taskToDelete = findTaskById(id);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted: " + taskToDelete.getName());
            return true;
        } else {
            System.out.println("Task not found with ID: " + id);
            return false;
        }
    }

    public void displayTask(int id) {
        Task taskToDisplay = findTaskById(id);
        if (taskToDisplay != null) {
            displayTask(taskToDisplay);
        } else {
            System.out.println("Task not found with ID: " + id);
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
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
        System.out.println("Reward: " + task.getReward());
        // Display subtasks and skills if needed
    }

    public int getNextAvailableId() {
        int maxId = 0;
        for (Task task : tasks) {
            maxId = Math.max(maxId, task.getId());
            int subTaskMaxId = getMaxSubtaskId(task);
            maxId = Math.max(maxId, subTaskMaxId);
        }
        return maxId + 1;
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
}
