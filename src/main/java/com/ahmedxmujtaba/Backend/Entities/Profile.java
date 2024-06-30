package com.ahmedxmujtaba.Backend.Entities;

import com.ahmedxmujtaba.Backend.Utility.Date;

import java.io.Serializable;
import java.time.LocalDate;

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
    public Profile(String name, String description, String DOB, String _class_, double exp, int level, int gems){
        this.name = name;
        this.description = description;
        this.DOB = DOB;
        this._class_ = _class_;
        this.exp = exp;
        this.level = level;
        this.gems = gems;
        this.age = calculateAge();
    }

    private double calculateAge() {
        //todo calculate date and fix the entry of a new profile in load profile
            return Date.ageCalculation(DOB);
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
        this.age = calculateAge();
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
