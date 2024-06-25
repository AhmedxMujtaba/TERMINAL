package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Profile;
import com.ahmedxmujtaba.Backend.Utility.Date;

import java.time.LocalDate;

public class ProfileCommands implements Command {
private Profile profile;
    public ProfileCommands(Profile profile){
    this.profile = profile;
}
    @Override
    public boolean execute(String[] arguments) {
        if (arguments.length > 0) {
            String subCommand = arguments[0];
            switch (subCommand) {
                case "-edit", "-e", "-E":
                    if (arguments.length > 2) {
                        String paramToChange = arguments[1];
                        switch (paramToChange) {
                            case "name", "n":
                                // call function to change name
                                return changeName(arguments[2]);
                            case "dob":
                                // function to change dob
                                return changeDOB(arguments[2]);
                            case "class", "c":
                                // function to change class
                                return changeClass(arguments[2]);
                            case "description", "d":
                                // function to change description
                                return changeDescription(arguments[2]);
                            default:
                                System.out.println("Invalid Command: Profile -[field] [value] \n Profile -help to see options");
                                return false;
                        }
                    }
                    else {
                        System.out.println("Invalid Command: Profile -[field] [value] \n Profile -help to see options");
                        return false;
                    }
                case "-view", "-display", "v":
                    // display profile
                    displayProfile();
                    return false; //no need to save any data since none is updated
                case "-help", "-h":
                    // display help
                    displayHelp();
                    return false;
                default:
                    System.out.println("Invalid Command: Profile -[field] \n-help to see options");
                    return false;
            }
        } else {
            System.out.println("Usage: Profile <field> [options]");
            return false;
        }
    }

    private boolean changeName(String newName) {
        profile.setName(newName);
        System.out.println("Name changed to: " + newName);
        return true;
    }

    private boolean changeDOB(String inputDate) {
        if (Date.isValidDate(inputDate)) {
            LocalDate date = Date.parseDate(inputDate);
            String formattedDate = Date.formatDate(date);
            profile.setDOB(formattedDate);
            System.out.println("Date of Birth changed to: " + formattedDate);
            return true;
        } else {
            System.out.println("Invalid date format. \nPlease enter date as dd/MM/yyyy");
            return false;
        }
    }
    private boolean changeClass(String newClass) {
        profile.set_class_(newClass);
        System.out.println("Class changed to: " + newClass);
        return true;
    }

    private boolean changeDescription(String newDescription) {
        profile.setDescription(newDescription);
        System.out.println("Description changed to: " + newDescription);
        return true;
    }

    private void displayHelp() {
        // logic to display help information
        System.out.println("Profile Commands:" +
                "\n-edit, -e, -E <field> <value> : Edit profile fields\n" +
                "-view, -v, -display : View profile\n" +
                "-help, -h : Display this help message\n" +
                "Fields: " +
                "\nname (n)" +
                "\ndob" +
                "\nclass (c)" +
                "\ndescription (d)");
    }
    public void displayProfile(){
        System.out.println("Name: " + profile.getName());
        System.out.println("Class: " + profile.get_class_());
        System.out.println("Lvl: " + profile.getLevel());
        System.out.println("Exp: " + profile.getExp());
        System.out.println("DOB: " + profile.getDOB());
        System.out.println("Age: "+profile.getAge());
        System.out.println("Description: " + profile.getDescription());
    }
}
