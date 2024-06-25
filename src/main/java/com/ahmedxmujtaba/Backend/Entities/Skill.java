package com.ahmedxmujtaba.Backend.Entities;

import java.util.ArrayList;

public class Skill {

    private String name;
    private String description;
    private double totalExp;
    private int lvl;
    private ArrayList<Skill> subskills;

    public Skill(String name, String description, double totalExp, int lvl, ArrayList<Skill> subskills){
        this.name = name;
        this.description = description;
        this.totalExp = totalExp;
        this.lvl = lvl;
        this.subskills = subskills;
    }

    public Skill (String name, String description){
    this.name = name;
    this.description = description;
    this.totalExp = 0;
    this.lvl = 0;
    this.subskills = new ArrayList<Skill>();
    }

    public ArrayList<Skill> getSubskills() {
        return subskills;
    }

    public void addSubskill(Skill skill){
        subskills.add(skill);
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public double getTotalExp() {
        return totalExp;
    }

    public int getLvl() {
        return lvl;
    }
}

//todo Might remove parent id later
