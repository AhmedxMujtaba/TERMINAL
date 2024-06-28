package com.ahmedxmujtaba;

import com.ahmedxmujtaba.Backend.IO.IOManager;
import com.ahmedxmujtaba.Commands.CommandManager;
import com.ahmedxmujtaba.Commands.LoadCommands;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        input();
    }

    public static void input(){
        LoadCommands loadCommands = new LoadCommands();
        CommandManager cmd = loadCommands.getCmd();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (type 'exit' to quit):");

        while (true) {
            System.out.print("<"+ loadCommands.getProfile().getName()+"> ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            int change = cmd.executeCommand(input);
            IOManager.saveFileOnChange(change, loadCommands);
        }
    }
}
