package com.ahmedxmujtaba.Backend.Entities;

import com.ahmedxmujtaba.Backend.Utility.Time;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private int id;
    private String name;
    private String description;
    private Difficulty difficulty;
    private Priority priority;
    private double expPoints;
    private LocalDate dueDate;
    private String dateCreated;
    private String timeDue;
    private Time duration;
    private ArrayList<Skill> skills;
    private boolean completionStatus;
    private boolean isSubtask;
    private ArrayList<Task> subtasks;
    private Reward reward;

    //task entity, make new task, store data in file, read data from file task.
    public Task (int id,String name, String description, Difficulty difficulty, double expPoints, Priority priority,
                LocalDate dueDate, String timeDue,String dateCreated, Time duration, ArrayList<Skill> skill,
                 boolean completionStatus, boolean isSubtask, Reward reward) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.expPoints = expPoints;
        this.priority = priority;
        this.dueDate = dueDate;
        this.timeDue = timeDue;
        this.dateCreated = dateCreated;
        this.duration = duration;
        this.skills = skill;
        this.completionStatus = completionStatus;
        this.reward = reward;
        this.isSubtask = isSubtask;
    }
    public Task (int id,String name, String description, Difficulty difficulty, double expPoints, Priority priority,
                 LocalDate dueDate, String timeDue,String dateCreated, Time duration, ArrayList<Skill> skill,
                 boolean completionStatus, boolean isSubtask, ArrayList<Task> subtasks, Reward reward) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.expPoints = expPoints;
        this.priority = priority;
        this.dueDate = dueDate;
        this.timeDue = timeDue;
        this.dateCreated = dateCreated;
        this.duration = duration;
        this.skills = skill;
        this.completionStatus = completionStatus;
        this.reward = reward;
        this.subtasks = subtasks;
        this.isSubtask = isSubtask;
    }
    public Task (String name){this.name = name;}

    //todo make getters and setters as required
}
