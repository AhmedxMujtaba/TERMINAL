package com.ahmedxmujtaba.Backend.IO;

import com.ahmedxmujtaba.Backend.Entities.Profile;
import com.ahmedxmujtaba.Backend.Entities.SkillTree;
import com.ahmedxmujtaba.Backend.Entities.Task;

public class IOManager {
    //todo make the following files:
    // tasks
    // daily tasks
    // profile
    // skill tree
    // quests

    private static String taskFileName = "Tasks.ser";
    private static String dailyTasksFileName = "Daily_Tasks.ser";
    private static String profileFileName = "Profile.ser";
    private static String skillTreeFileName = "Skill_Tree.ser";
    private static String questFileName = "Quests.ser";

    //save Tasks
    public static boolean saveTasks(Task task){
        if (IO.saveFile(taskFileName,task)) return true;
        else return false;
    }
    public static boolean saveProfile(Profile profile){
        if (IO.saveFile(profileFileName,profile)) return true;
        else return false;
    }
    public static boolean saveSkillTree(SkillTree skillTree){
        if (IO.saveFile(skillTreeFileName,skillTree)) return true;
        else return false;
    }
}
