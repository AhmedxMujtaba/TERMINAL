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
        CommandManager cmd = LoadCommands.getCmd();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (type 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            if(cmd.executeCommand(input)){
                //todo add function to save file
            };

        }
    }

}
