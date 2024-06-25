package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Profile;

public class ProfileCommands implements Command {
private Profile profile;
    public ProfileCommands(Profile profile){
    this.profile = profile;
}
    @Override
    public void execute(String[] arguments) {
        if (arguments.length > 0) {
            String subCommand = arguments[0];
            switch (subCommand) {
                case "-edit", "-e", "-E":
                    if (arguments.length > 2) {
                        String paramToChange = arguments[1];
                        switch (paramToChange) {
                            case "name", "n":
                                // call function to change name
                                changeName(arguments[2]);
                                break;
                            case "dob":
                                // function to change dob
                                changeDOB(arguments[2]);
                                break;
                            case "class", "c":
                                // function to change class
                                changeClass(arguments[2]);
                                break;
                            case "description", "d":
                                // function to change description
                                changeDescription(arguments[2]);
                                break;
                            default:
                                System.out.println("Invalid Command: Profile -[field] [value] \n Profile -help to see options");
                                break;
                        }
                    }
                    else {
                        System.out.println("Invalid Command: Profile -[field] [value] \n Profile -help to see options");
                    }
                    break;
                case "-view", "-display", "v":
                    // display profile
                    displayProfile();
                    break;
                case "-help", "-h":
                    // display help
                    displayHelp();
                    break;
                default:
                    System.out.println("Invalid Command: Profile -[field] \n-help to see options");
                    break;
            }
        } else {
            System.out.println("Usage: Profile <field> [options]");
        }
    }

    private void changeName(String newName) {
        profile.setName(newName);
        System.out.println("Name changed to: " + newName);
    }

    private void changeDOB(String newDOB) {

        System.out.println("DOB changed to: " + newDOB);
    }

    private void changeClass(String newClass) {
        // logic to change the class
        System.out.println("Class changed to: " + newClass);
    }

    private void changeDescription(String newDescription) {
        // logic to change the description
        System.out.println("Description changed to: " + newDescription);
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
