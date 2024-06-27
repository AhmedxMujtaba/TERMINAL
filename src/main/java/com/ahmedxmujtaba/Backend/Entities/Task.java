package com.ahmedxmujtaba.Backend.Entities;

import com.ahmedxmujtaba.Backend.Utility.DurationTime;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;
    private Difficulty difficulty;
    private Priority priority;
    private double expPoints;
    private LocalDate dueDate;
    private LocalDate dateCreated;
    private LocalTime timeDue;
    private DurationTime duration;
    private ArrayList<Skill> skills;
    private boolean completionStatus;
    private ArrayList<Task> subtasks;
    private Gems gems;

    //task entity, make new task, store data in file, read data from file task.
    public Task (int id, String name, String description, Difficulty difficulty, double expPoints, Priority priority,
                 LocalDate dueDate, LocalTime timeDue, LocalDate dateCreated, DurationTime duration, ArrayList<Skill> skill,
                 boolean completionStatus, Gems gems) {

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
        this.gems = gems;
    }
    //case if task is generated for first time
    public Task (String name, String description, Difficulty difficulty, double expPoints, Priority priority,
                 LocalDate dueDate, LocalTime timeDue, LocalDate dateCreated, DurationTime duration, ArrayList<Skill> skill,
                 boolean completionStatus, ArrayList<Task> subtasks, Gems gems) {

        //this.id = idGenerator();
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
        this.gems = gems;
        this.subtasks = subtasks;
    }
    public Task (String name){
        this.name = name;
        //this.id = idGenerator();

    }

    //getters
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Task> getSubtasks() {
        return subtasks;
    }
    public String getDescription() {
        return description;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public boolean getCompletionStatus() {
        return completionStatus;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public double getExpPoints() {
        return expPoints;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Gems getReward() {
        return gems;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalTime getTimeDue() {
        return timeDue;
    }

    public DurationTime getDuration() {
        return duration;
    }

    //setters

    public void setSubtasks(ArrayList<Task> subtasks){
        this.subtasks=subtasks;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDuration(DurationTime duration) {
        this.duration = duration;
    }

    public void setExpPoints(double expPoints) {
        this.expPoints = expPoints;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setReward(Gems gems) {
        this.gems = gems;
    }

    public void setTimeDue(LocalTime timeDue) {
        this.timeDue = timeDue;
    }

    public void addSubtask(Task newTask) {
        subtasks.add(newTask);
    }
}
