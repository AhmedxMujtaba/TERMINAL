package com.ahmedxmujtaba.Backend.Entities;

import java.util.ArrayList;

public class Skill {

    private String name;
    private String description;
    private double totalExp;
    private int parentId;
    private ArrayList<Skill> subskills;

    public Skill(String name, String description, double totalExp, int parentId, ArrayList<Skill> subskills){
        this.name = name;
        this.description = description;
        this.totalExp = totalExp;
        this.parentId = parentId;
        this.subskills = subskills;
    }

    public Skill (String name, String description){
    this.name = name;
    this.description = description;
    this.totalExp = 0;
    this.parentId = -1;
    this.subskills = null;
    }

    public ArrayList<Skill> getSubskills() {
        return subskills;
    }
}
//todo Might remove partent id later
