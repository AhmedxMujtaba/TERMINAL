package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SkillTree implements Serializable {
    private ArrayList<Skill> skills;

    public SkillTree() {
        //initialize base skills (can be changed later)
        Skill intl = new Skill("Mental", "Features the skills that require intelligence.");
        Skill phy = new Skill("Physical", "Features the skills that deal with Physical skill.");
        Skill charm = new Skill("Charm", "Skills that deal with charm and looks, speech etc.");
        this.skills = new ArrayList<>(List.of(intl, phy, charm));
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    // add one skill
    public void addMainSkill(Skill skill) {
        this.skills.add(skill);
    }

    // function to add a subskill of the skill in the skill tree list
    public boolean addSubskill(String parentName, Skill skill) {
        Skill parent = findSkillByName(parentName);
        if (parent != null) {
            parent.addSubskill(skill);
            return true;
        }
        return false;
    }

    // helper function to find skill by name
    public Skill findSkillByName(String name) {
        for (Skill skill : skills) {
            Skill found = findSkillByNameRecursive(skill, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private Skill findSkillByNameRecursive(Skill current, String name) {
        if (current.getName().equals(name)) {
            return current;
        }
        if (current.getSubskills() != null) {
            for (Skill subskill : current.getSubskills()) {
                Skill found = findSkillByNameRecursive(subskill, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    // function to delete a skill by name
    public boolean deleteSkill(String name) {
        for (int i = 0; i < skills.size(); i++) {
            if (skills.get(i).getName().equals(name)) {
                skills.remove(i);
                return true;
            } else if (deleteSkillRecursive(skills.get(i), name)) {
                return true;
            }
        }
        return false;
    }

    private boolean deleteSkillRecursive(Skill parent, String name) {
        List<Skill> subskills = parent.getSubskills();
        if (subskills != null) {
            for (int i = 0; i < subskills.size(); i++) {
                if (subskills.get(i).getName().equals(name)) {
                    subskills.remove(i);
                    return true;
                } else if (deleteSkillRecursive(subskills.get(i), name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }
}
