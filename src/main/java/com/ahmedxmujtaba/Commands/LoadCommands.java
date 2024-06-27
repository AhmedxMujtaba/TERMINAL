package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Profile;
import com.ahmedxmujtaba.Backend.Entities.Skill;
import com.ahmedxmujtaba.Backend.Entities.SkillTree;
import com.ahmedxmujtaba.Backend.Entities.TaskList;
import com.ahmedxmujtaba.Backend.IO.IOManager;

import java.util.ArrayList;


public class LoadCommands {
    private static CommandManager cmd =  new CommandManager();
    public LoadCommands() {}

    public static void commandLoader(){
            IOManager.makeDataDir();
            SkillTree skillTree = IOManager.loadSkillTree();
            Profile profile = IOManager.loadProfile();
            TaskList taskList = IOManager.loadTasks();
            cmd.registerCommand("Profile", new ProfileCommands(profile));
            cmd.registerCommand("Skill", new SkillCommands(skillTree));
            cmd.registerCommand("Task", new TaskCommands(skillTree,taskList));
    }

    public static CommandManager getCmd() {
        commandLoader();
        return cmd;
    }
}
