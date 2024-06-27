package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Profile;
import com.ahmedxmujtaba.Backend.Entities.SkillTree;
import com.ahmedxmujtaba.Backend.Entities.TaskList;
import com.ahmedxmujtaba.Backend.IO.IOManager;

public class LoadCommands {
    private static CommandManager cmd =  new CommandManager();
    private SkillTree skillTree;
    private Profile profile;
    private TaskList taskList;
    public LoadCommands() {
        commandLoader();
    }

    public void commandLoader(){
            IOManager.makeDataDir();
            this.skillTree = IOManager.loadSkillTree();
            this.profile = IOManager.loadProfile();
            this.taskList = IOManager.loadTasks();
            cmd.registerCommand("Profile", new ProfileCommands(profile));
            cmd.registerCommand("Skill", new SkillCommands(skillTree));
            cmd.registerCommand("Task", new TaskCommands(skillTree,taskList));
    }

    public CommandManager getCmd() {
        commandLoader();
        return cmd;
    }

    public SkillTree getSkillTree() {
        return skillTree;
    }

    public Profile getProfile() {
        return profile;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
