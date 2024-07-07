package com.ahmedxmujtaba;

import com.ahmedxmujtaba.Backend.Entities.Skill;
import com.ahmedxmujtaba.Backend.IO.IOManager;
import com.ahmedxmujtaba.Commands.CommandManager;
import com.ahmedxmujtaba.Commands.LoadCommands;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        displayInfo();
        input();
    }

    public static void input(){
        LoadCommands loadCommands = new LoadCommands();
        CommandManager cmd = loadCommands.getCmd();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("<"+ loadCommands.getProfile().getName()+"> ");
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("exit")) {
                break;
            }
            int change = cmd.executeCommand(input);
            IOManager.saveFileOnChange(change, loadCommands);
        }
    }
    public static void displayInfo(){
        System.out.println("\n" +
                " _____  _____ ______ ___  ___ _____  _   _   ___   _     \n" +
                "|_   _||  ___|| ___ \\|  \\/  ||_   _|| \\ | | / _ \\ | |    \n" +
                "  | |  | |__  | |_/ /| .  . |  | |  |  \\| |/ /_\\ \\| |    \n" +
                "  | |  |  __| |    / | |\\/| |  | |  | . ` ||  _  || |    \n" +
                "  | |  | |___ | |\\ \\ | |  | | _| |_ | |\\  || | | || |____\n" +
                "  \\_/  \\____/ \\_| \\_|\\_|  |_/ \\___/ \\_| \\_/\\_| |_/\\_____/\n");
        System.out.println("V 1.0\nCreated by Mujtaba\ngithub.com/AhmedxMujtaba\n");
        System.out.println("Commands: \nProfile \nTask \nSkill \nTimeTable \nExit \nType '[Command] -help' for more details\n ");

    }

}
