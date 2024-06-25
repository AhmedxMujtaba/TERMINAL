package com.ahmedxmujtaba.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public boolean executeCommand(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length == 0) {
            System.out.println("No command provided.");
            return false;
        }

        String commandName = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        Command command = commands.get(commandName);
        if (command != null) {
            return command.execute(args);

        } else {
            System.out.println("Unknown command: " + commandName);
            return false;
        }
    }
}
