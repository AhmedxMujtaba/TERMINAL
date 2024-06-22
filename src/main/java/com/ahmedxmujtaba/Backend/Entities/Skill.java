package com.ahmedxmujtaba.Backend.Entities;

public class Skill {

    private String name;
    private String description;
    private double totalExp;
    private int parentId;

    public Skill(String name, String description, double totalExp, int parentId){
        this.name = name;
        this.description = description;
        this.totalExp = totalExp;
        this.parentId = parentId;
    }

    public Skill(String name, String description, int parentId){
    this.name = name;
    this.description = description;
    this.parentId = parentId;
    }
}
