package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;

public class Profile implements Serializable {
    private String name;
    private String description;
    private String DOB;
    private double age; //todo add function to calculate age
    private String _class_;
    private double exp;
    private int level; //todo add function to calculate level
    private int gems;

    public Profile(String name){
        this.name = name;
    }
    public Profile(String name, String description, String DOB, int age, String _class_, double exp, int level, int gems){
        this.name = name;
        this.description = description;
        this.DOB = DOB;
        this._class_ = _class_;
        this.exp = exp;
        this.level = level;
        this.gems = gems;
    }
//getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAge() {
        return age;
    }

    public double getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public String get_class_() {
        return _class_;
    }

    public String getDOB() {
        return DOB;
    }

    public int getGems() {
        return gems;
    }

    //setters
    public void setAge(double age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void set_class_(String _class_) {
        this._class_ = _class_;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }
}
