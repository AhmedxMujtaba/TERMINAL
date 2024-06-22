package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SkillTree implements Serializable {
    private ArrayList<Skill> skills;

    public SkillTree(){
        //initialize base skills (can be changed later)
        Skill intl = new Skill("Mental","Features the skills that require intelligence.");
        Skill phy = new Skill("Physical","Features the skills that deal with Physical skill.");
        Skill charm = new Skill("Charm","Skills that deal with charm and looks, speech etc.");
        this.skills = new ArrayList<>(List.of(intl,phy,charm));
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
// add one skill
    public void addMainSkill(Skill skill) {
        this.skills.add(skill);
    }

    //function to add a subskill of the skill in the skill tree list
    public boolean addSubskill(String parentName,Skill skill){
        Skill parent = findSkillByName(parentName);
        if(parent != null)
        {
            parent.addSubskill(skill);
            return true;
        }
        return false;
    }
    //helper function to find parent
    public Skill findSkillByName(String name) {
        Skill currSkill = null;
        if (currSkill.getName().equals(name)) {
            return currSkill;
        }
        for (int i = 0; i < skills.size(); i++) {
            currSkill = skills.get(i);
            Skill foundSkill = findSkillByName(name);
            if (foundSkill != null) {
                return foundSkill;
            }
        }
        return null;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

}
