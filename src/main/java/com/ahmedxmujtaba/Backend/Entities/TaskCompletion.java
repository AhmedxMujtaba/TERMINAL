package com.ahmedxmujtaba.Backend.Entities;

import com.ahmedxmujtaba.Backend.IO.IOManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class TaskCompletion {

    private static final int baseExp = 100;
    private static final int coefficient = 50;

    private TaskCompletion() {
    }

    public static boolean taskDone(Task task, Profile profile, SkillTree skillTree) {
        double newExp = profile.getExp() + task.getExpPoints();
        profile.setLevel(calculatePlayerLevel(newExp));
        profile.setExp(newExp);
        //since the skills save in tasks are passes as reference (happens itself in java)
        //the data updates the Skill param in the skill tree.
        ArrayList<Skill> skills = task.getSkills();
        //getting all parents and sorting out the repetition
        ArrayList<Skill> tempSkills = task.getSkills();
//        for (Skill currSkill : skills) {
//            tempSkills.addAll(skillTree.allParentSkills(currSkill));
//        }

//        for (int i = 0; i < tempSkills.size(); i++) {
//            for (int j = i + 1; j < tempSkills.size(); j++) {
//                if (tempSkills.get(i).equals(tempSkills.get(j))) {
//                    tempSkills.remove(j);
//                    j--; // Decrement j because the list size has changed
//                }
//            }
//        }

        //now add the exp to all skills
        for (int i = 0; i < tempSkills.size() ; i++) {
            Skill currSkill = tempSkills.get(i);
            addExpToSkill(currSkill, task.getExpPoints());
            System.out.println(currSkill.getTotalExp());
        }
        System.out.println(IOManager.saveSkillTree(skillTree) ? "Skill save" : "skill no save");
        profile.setGems(profile.getGems() + task.getGems().getAmount());
        if (IOManager.saveProfile(profile)) {
            System.out.println("Exp Gained: " + task.getExpPoints() + "\nLevel: " + profile.getLevel());
            return true;
        } else {
            System.out.println("Error in setting exp");
        }
        return false;
    }

    public static boolean taskDone(RepetitiveTask task, Profile profile, SkillTree skillTree) {
        double newExp = profile.getExp() + task.getExpPoints();
        profile.setLevel(calculatePlayerLevel(newExp));
        profile.setExp(newExp);
        ArrayList<Skill> skills = task.getSkills();
        for (Skill currSkill : skills) {
            addExpToSkill(currSkill,task.getExpPoints());
        }
        IOManager.saveSkillTree(skillTree);
        profile.setGems(profile.getGems() + task.getGems().getAmount());
        if (IOManager.saveProfile(profile)) {
            System.out.println("Exp Gained: " + task.getExpPoints() + "\nLevel: " + profile.getLevel());
            return true;
        } else {
            System.out.println("Error in setting exp");
        }
        return false;
    }

    public static int quadraticExpCal(int level, int baseExp, int coefficient) {
        if (level == 0) {
            return 0; // 0 EXP for level 0
        }
        return baseExp + coefficient * (int) Math.pow(level - 1, 2);
    }


    public static int calculatePlayerLevel(double playerExp) {
        int level = 1;
        while (true) {
            int expNeeded = quadraticExpCal(level, baseExp, coefficient);
            if (playerExp < expNeeded) {
                break;
            }
            level++;
        }
        return level - 1;
    }

    public static void taskUndone(Task task, Profile profile) {
        // Implement this method my niga
    }

    public static void taskUndone(RepetitiveTask task, Profile profile) {
        //  Implement this method
    }

    private static void addExpToSkill(Skill currSkill, double expPoints) {
        if (currSkill == null) return;
        double totalExpOfSkill = currSkill.getTotalExp() + expPoints;
        currSkill.setTotalExp(totalExpOfSkill);
        currSkill.setLvl(calculatePlayerLevel(totalExpOfSkill));
        System.out.println("Skill: " + currSkill.getName() + " gained " + expPoints + " Exp\nSkill Level: " + currSkill.getLvl());
    }

    public static void displayLevelBar(double currentExp) {
        int currentLevel = calculatePlayerLevel(currentExp);
        int expNextLvl;
        expNextLvl = calculateRequiredExpForLevel(currentLevel + 1);
        int expPrevLvl = calculateRequiredExpForLevel(currentLevel);
        double percentageCompletion = ((currentExp - expPrevLvl) / (expNextLvl - expPrevLvl)) * 100;
        lvlBarPrinter(percentageCompletion);
    }

    private static int calculateRequiredExpForLevel(int lvl) {
        return quadraticExpCal(lvl, baseExp, coefficient);
    }

    private static void lvlBarPrinter(double percentage) {
        // ANSI escape codes for green and reset color
        String GREEN = "\033[0;32m";

        String RESET = "\033[0m";

        // Ensure percentage is between 0 and 100
        if (percentage < 0) percentage = 0;
        if (percentage > 100) percentage = 100;

        int totalBars = 30; // Total length of the progress bar
        int parts = (int) (percentage / 100 * totalBars); // Calculate the number of filled bars

        // Print the top border
        System.out.println("╔════════════════════════════════════════╗");
        System.out.print("║");

        // Print the filled parts of the progress bar in green
        System.out.print(GREEN);
        for (int i = 0; i < parts; i++) {
            System.out.print("█");
        }
        System.out.print(RESET);

        // Print the unfilled parts of the progress bar
        for (int i = parts; i < totalBars; i++) {
            System.out.print("░");
        }

        // Print the closing part of the progress bar and the percentage
        System.out.printf("║ %5.1f%%\n", percentage);

        // Print the bottom border
        System.out.println("╚════════════════════════════════════════╝");
    }
}
