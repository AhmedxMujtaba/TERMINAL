package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;
import java.util.SplittableRandom;

public class Profile implements Serializable {
    private String name;
    private String description;
    private String DOB;
    private double Age;
    private String _class_;
    private double exp;
    private int level;

    public Profile(String name){
        this.name = name;
    }
    public Profile(String name, String description, String DOB, int age, String _class_, double exp, int level){
        this.name = name;
        this.description = description;
        this.DOB = DOB;
        this._class_ = _class_;
        this.exp = exp;
        this.level = level;
    }
//getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAge() {
        return Age;
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
//setters
    public void setAge(double age) {
        Age = age;
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
}
