package com.ahmedxmujtaba.Backend.Entities;

import com.ahmedxmujtaba.Backend.Utility.ClockTime;

import java.io.Serializable;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;


public class RepetitiveTaskList implements Serializable {
    private ArrayList<RepetitiveTask> tasks;

    public RepetitiveTaskList()
    {
        this.tasks = new ArrayList<RepetitiveTask>();
    }

    public ArrayList<RepetitiveTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<RepetitiveTask> tasks) {
        this.tasks = tasks;
    }

    public RepetitiveTask findTaskByName(String name) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().equals(name)) return tasks.get(i);
        }
        return null;
    }


    public boolean deleteTask(String name) {
        RepetitiveTask taskToDelete = findTaskByName(name);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted: " + taskToDelete.getName());
            return true;
        } else {
            System.out.println("Task not found with Name: " + name);
            return false;
        }
    }

    public void displayTaskDetails(RepetitiveTask task) {
        //check to make sure that the time due for task is over and set completion to false
        checkTimeDueOver(task);
        System.out.println("Name: " + task.getName());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Difficulty: " + task.getDifficulty());
        System.out.println("Priority: " + task.getPriority());
        System.out.println("Exp Points: " + task.getExpPoints());
        System.out.println("Time Due: " + task.getTimeDue());
        System.out.println("Date Created: " + task.getDateCreated());
        System.out.println("Duration: " + task.getDuration());
        System.out.println("Completion Status: " + task.getCompletionStatus());
        System.out.println("Gems: " + task.getGems().getAmount());
        System.out.println("Skills: ");
        if(!task.getSkills().isEmpty())for (int i = 0; i<task.getSkills().size();i++) System.out.println("-"+task.getSkills().get(i).getName());
        else System.out.println("No Skills Selected");

    }

    private ArrayList<RepetitiveTask> sortByTime() {
        int n = tasks.size();
        ArrayList<RepetitiveTask> nullTimeTasks = new ArrayList<>();

        // First, separate out the tasks with null TimeDue
        for (int i = 0; i < n; i++) {
            if (tasks.get(i).getTimeDue() == null) {
                nullTimeTasks.add(tasks.get(i));
                tasks.remove(i);
                i--; // adjust the index after removal
                n--; // adjust the size after removal
            }
        }

        // Implementing selection sort for tasks with non-null TimeDue
        n = tasks.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (tasks.get(j).getTimeDue().isBefore(tasks.get(minIndex).getTimeDue())) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            RepetitiveTask temp = tasks.get(i);
            tasks.set(i, tasks.get(minIndex));
            tasks.set(minIndex, temp);
        }

        // Append the tasks with null TimeDue to the end
        tasks.addAll(nullTimeTasks);

        return tasks;
    }

    public void displayFormatedTimeTabelUsingRepetitiveTasks() {
        ArrayList<RepetitiveTask> repetitiveTasks = sortByTime();
        System.out.printf("%-15s %-10s %-15s %-5s %-5s%n", "Name", "Time Due", "Is Completed", "Exp", "Gems");
            // Print the task details
            for (int i = 0; i < tasks.size(); i++) {
                RepetitiveTask task = tasks.get(i);
                checkTimeDueOver(task);
                System.out.printf("%-15s %-10s %-15s %-5.1f %-5d%n",
                        task.getName(),
                        ClockTime.parseLocalTimeTo12HourString(task.getTimeDue()),
                        task.getCompletionStatus() ? "Yes" : "No",
                        task.getExpPoints(),
                        task.getGems().getAmount());
            }
    }
    private void checkTimeDueOver(RepetitiveTask task){
        if (task.getTimeDue() == null) return;
        if(task.getTimeDue().isBefore(ClockTime.getCurrentLocalTime()))
            task.setCompletionStatus(false);
    }
}

//todo function to view subtask if current tasked being viewed has no parent