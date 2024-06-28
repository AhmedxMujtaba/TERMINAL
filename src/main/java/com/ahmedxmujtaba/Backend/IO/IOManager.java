package com.ahmedxmujtaba.Backend.IO;

import com.ahmedxmujtaba.Backend.Entities.Profile;
import com.ahmedxmujtaba.Backend.Entities.SkillTree;
import com.ahmedxmujtaba.Backend.Entities.TaskList;
import com.ahmedxmujtaba.Commands.LoadCommands;

public class IOManager {
    //todo make the following files:
    // tasks
    // daily tasks
    // profile
    // skill tree
    // quests

    private static String folderDir = "../Data";
    private static String taskFileDir = folderDir+"/Task_List.ser";
    private static String dailyTasksFileDir = folderDir+"/Daily_Tasks.ser";
    private static String profileFileDir = folderDir+"/Profile.ser";
    private static String skillTreeFileDir = folderDir+"/Skill_Tree.ser";
    private static String questFileDir = folderDir+"/Quests.ser";


    //save Tasks
    public static boolean saveTasks(TaskList taskList){
        if (IO.saveFile(taskFileDir,taskList)) return true;
        else return false;
    }
    public static boolean saveProfile(Profile profile){
        if (IO.saveFile(profileFileDir,profile)) return true;
        else return false;
    }
    public static boolean saveSkillTree(SkillTree skillTree){
        if (IO.saveFile(skillTreeFileDir,skillTree)) return true;
        else return false;
    }
    public static SkillTree loadSkillTree(){
        SkillTree skillTree = IO.loadFile(skillTreeFileDir);
        if (skillTree != null) return skillTree;
        //if no skill tree exists return new since first program runs

        else {
            SkillTree newSkillTree = new SkillTree();
            if(saveSkillTree(newSkillTree))System.out.println("Skill_Tree.ser Created");
            return newSkillTree;
        }
    }

    public static Profile loadProfile(){
        Profile profile = IO.loadFile(profileFileDir);
        if (profile != null) return profile;
        else
        {
            Profile newProfile = new Profile("Struggler");
            if(saveProfile(newProfile)) System.out.println("Profile.ser Created");
            return newProfile;
        }
    }
    public static TaskList loadTasks(){
        TaskList taskList = IO.loadFile(taskFileDir);
        if (taskList != null) return taskList;
        else
        {
            TaskList newTaskList = new TaskList();
            if(saveTasks(newTaskList))System.out.println("Task_List.ser Created");
            return newTaskList;
        }
    }
    public static void makeDataDir(){
        IO.createFolderIfNotExists(folderDir);
    }
    public static void saveFileOnChange(int change, LoadCommands loadCommands) {

        //1 if skills are changed,
        //2 if tasks are changed,
        //3 if Profile is changed
        //4 if repetitive tasks are changed

        switch (change){
            case 1:
                if(saveSkillTree(loadCommands.getSkillTree())) System.out.println("Changes Saved 'Skills'");
                break;
            case 2:
                if(saveTasks(loadCommands.getTaskList())) System.out.println("Changes Saved 'Tasks'");
                break;
            case 3:
                if(saveProfile(loadCommands.getProfile())) System.out.println("Changes Saved 'Profile'");
                break;
            default:
                //no change
                break;
        }
    }
}
