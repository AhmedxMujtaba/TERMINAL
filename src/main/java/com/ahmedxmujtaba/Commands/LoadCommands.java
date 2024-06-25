package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Profile;
import com.ahmedxmujtaba.Backend.Entities.SkillTree;


public class LoadCommands {
    private static CommandManager cmd =  new CommandManager();;
    public LoadCommands() {}

    public static void commandLoader(){
            //todo load profile from file
            cmd.registerCommand("Profile", new ProfileCommands(new Profile("ahmed")));
            cmd.registerCommand("Skills", new SkillCommands(new SkillTree()));

    }

    public static CommandManager getCmd() {
        commandLoader();
        return cmd;
    }
}
