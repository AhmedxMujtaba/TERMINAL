package com.ahmedxmujtaba.Backend.Entities;

import com.ahmedxmujtaba.Backend.Utility.DurationTime;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class RepetitiveTask implements Serializable {
    private int id;
    private String name;
    private String description;
    private Difficulty difficulty;
    private Priority priority;
    private double expPoints;
    private LocalDate dateCreated;
    private LocalTime timeDue;
    private DurationTime duration;
    private ArrayList<Skill> skills;
    private boolean completionStatus;
    private Gems gems;

    //task entity, make new task, store data in file, read data from file task.
    public RepetitiveTask(int id, String name, String description, Difficulty difficulty, double expPoints, Priority priority, LocalTime timeDue, LocalDate dateCreated, DurationTime duration, ArrayList<Skill> skill,
                          boolean completionStatus, Gems gems) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.expPoints = expPoints;
        this.priority = priority;
        this.timeDue = timeDue;
        this.dateCreated = dateCreated;
        this.duration = duration;
        this.skills = skill;
        this.completionStatus = completionStatus;
        this.gems = gems;
    }
    //case if task is generated for first time
    public RepetitiveTask (String name, String description, Difficulty difficulty, double expPoints, Priority priority, LocalTime timeDue, LocalDate dateCreated, DurationTime duration, ArrayList<Skill> skill,
                 boolean completionStatus, Gems gems) {

        //this.id = idGenerator();
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.expPoints = expPoints;
        this.priority = priority;
        this.timeDue = timeDue;
        this.dateCreated = dateCreated;
        this.duration = duration;
        this.skills = skill;
        this.completionStatus = completionStatus;
        this.gems = gems;

    }
    public RepetitiveTask (String name){
        this.name = name;

    }

    //getters
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
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


    public Priority getPriority() {
        return priority;
    }

    public Gems getGems() {
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

    public void setDuration(DurationTime duration) {
        this.duration = duration;
    }

    public void setExpPoints(double expPoints) {
        this.expPoints = expPoints;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setGems(Gems gems) {
        this.gems = gems;
    }

    public void setTimeDue(LocalTime timeDue) {
        this.timeDue = timeDue;
    }

}
