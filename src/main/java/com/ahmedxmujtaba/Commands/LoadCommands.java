package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Profile;


public class LoadCommands {
    private static CommandManager cmd;
    public LoadCommands() {

        this.cmd = new CommandManager();
    }

    private void commandLoader(){
            //todo load profile from file
            Profile profile = new Profile("niga");
            cmd.registerCommand("Profile", new ProfileCommands(profile));



    }

    public static CommandManager getCmd() {
        return cmd;
    }
}
