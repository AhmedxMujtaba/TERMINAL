package com.ahmedxmujtaba.Commands;


import java.util.Stack;

public interface Command {

//    private static final String[] profile = {"P","Profile"};
//    private static final String[] skillTree = {"Skill","Skills","S"};
//    private static final String[] tasks = {"Task","Tasks","T"};
//    private static final String[] edit = {"Edit","E"};
//    private static final String[] add = {"Add","A","Create","C"};
//    private static final String[] delete = {"Delete","De","Remove","R"};
//    private static final String[] display = {"Display", "Dis"};
//    private static final String[] details = {"Display", "Dis"};
//    private static final String[] markAsDone = {"Done", "Complete", "C"};
//    private Stack<String> previousCommands;

    public boolean execute(String[] arguments);
}
