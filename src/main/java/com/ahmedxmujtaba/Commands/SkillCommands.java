package com.ahmedxmujtaba.Commands;

import com.ahmedxmujtaba.Backend.Entities.Skill;
import com.ahmedxmujtaba.Backend.Entities.SkillTree;

import java.util.List;

public class SkillCommands implements Command {
    private SkillTree skillTree;

    public SkillCommands(SkillTree skillTree) {
        this.skillTree = skillTree;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (arguments.length > 0) {
            String subCommand = arguments[0];
            switch (subCommand) {
                case "-addMainSkill", "-ms":
                    if (arguments.length > 2) {
                        String name = arguments[1];
                        String description = arguments[2];
                        return addMainSkill(name, description);
                    } else {
                        System.out.println("Usage: -addMainSkill <name> <description>");
                        return false;
                    }
                case "-addSubskill", "-s":
                    if (arguments.length > 3) {
                        String parentName = arguments[1];
                        String name = arguments[2];
                        String description = arguments[3];
                        return addSubskill(parentName, name, description);
                    } else {
                        System.out.println("Usage: -addSubskill <parentName> <name> <description>");
                        return false;
                    }
                case "-editSkill", "-e":
                    if (arguments.length > 3) {
                        String skillName = arguments[1];
                        String field = arguments[2];
                        String newValue = arguments[3];
                        return editSkill(skillName, field, newValue);
                    } else {
                        System.out.println("Usage: -editSkill <name> <field> <newValue>");
                        return false;
                    }
                case "-deleteSkill", "-del":
                    if (arguments.length > 1) {
                        String skillName = arguments[1];
                        return deleteSkill(skillName);
                    } else {
                        System.out.println("Usage: -deleteSkill <name>");
                        return false;
                    }
                case "-viewTree", "-t","-tree":
                    viewTree();
                    return false;
                case "-help", "-h":
                    displayHelp();
                    return false;
                case "-display", "-d":
                    if (arguments.length > 1) {
                        String skillName = arguments[1];
                        displaySkillDetails(skillName);
                    } else {
                        System.out.println("Usage: -display <skill name>");
                    }
                    return false;
                default:
                    System.out.println("Invalid Command: Skill -[field] \n-help to see options");
                    return false;
            }
        } else {
            System.out.println("Usage: Skill <field> [options]");
            return false;
        }
    }

    private boolean addMainSkill(String name, String description) {
        Skill skill = new Skill(name, description);
        skillTree.addMainSkill(skill);
        System.out.println("Main skill added: " + name);
        return true;
    }

    private boolean addSubskill(String parentName, String name, String description) {
        Skill skill = new Skill(name, description);
        if (skillTree.addSubskill(parentName, skill)) {
            System.out.println("Subskill " + name + " added under " + parentName);
            return true;
        } else {
            System.out.println("Parent skill not found: " + parentName);
            return false;
        }
    }

    private boolean editSkill(String skillName, String field, String newValue) {
        Skill skill = skillTree.findSkillByName(skillName);
        if (skill != null) {
            switch (field.toLowerCase()) {
                case "name", "n":
                    skill.setName(newValue);
                    System.out.println("Skill " + skillName + " updated with new name: " + newValue);
                    break;
                case "description","desc":
                    skill.setDescription(newValue);
                    System.out.println("Skill " + skillName + " updated with new description: " + newValue);
                    break;
                default:
                    System.out.println("Invalid field. Use 'name' or 'description'.");
                    return false;
            }
            return true;
        } else {
            System.out.println("Skill not found: " + skillName);
            return false;
        }
    }

    private boolean deleteSkill(String skillName) {
        if (skillTree.deleteSkill(skillName)) {
            System.out.println("Skill " + skillName + " deleted.");
            return true;
        } else {
            System.out.println("Skill not found: " + skillName);
            return false;
        }
    }

    private void viewTree() {
        System.out.println("Skill Tree:");
        for (Skill skill : skillTree.getSkills()) {
            printSkill(skill, "", true);
        }
    }

    private void printSkill(Skill skill, String indent, boolean last) {
        System.out.print(indent);
        if (last) {
            System.out.print("└─ ");
            indent += "   ";
        } else {
            System.out.print("├─ ");
            indent += "│  ";
        }
        System.out.println(skill.getName());

        if (skill.getSubskills() != null) {
            List<Skill> subskills = skill.getSubskills();
            for (int i = 0; i < subskills.size(); i++) {
                printSkill(subskills.get(i), indent, i == subskills.size() - 1);
            }
        }
    }
    private void displaySkillDetails(String skillName){
        Skill skill = skillTree.findSkillByName(skillName);
        if( skill == null) {
            System.out.println("Skill: " + skillName + " not found 404");
            return;
        }
        System.out.println(
                "\nName: "+skill.getName()
                +"\nLvl: "+skill.getLvl()
                +"\nExp: "+skill.getTotalExp()
                +"\nDescription: "+skill.getDescription()
                +"\nSub-skills: ");
        printSkill(skill,"",true);
    }

    private void displayHelp() {
        System.out.println("Skill Commands:");
        System.out.println("-addMainSkill, -ms <name> <description> : Add a main skill");
        System.out.println("-addSubskill, -s <parentName> <name> <description> : Add a subskill");
        System.out.println("-editSkill, -e <name> <field> <newValue> : Edit a skill's name or description\n" +
                "Fields: name (n), description (d)");
        System.out.println("-display, -d <name> : Display skill details");
        System.out.println("-deleteSkill, -del <name> : Delete a skill");
        System.out.println("-viewTree, -t, -tree : View the skill tree");
        System.out.println("-help, -h : Display this help message");
    }
}
