package model;

import commands.CommandFactory;

public class Parser {

    private CommandFactory commandCreator;
    private CommandNode head;

    /**
     * Parses a string and builds a tree of CommandNodes
     * @param s is the input string
     * @return the head node of a tree of CommandNodes
     */
    public CommandNode parse(String s) {
        String[] splitString = s.split(" ");
        commandCreator = new CommandFactory();
        // TODO: check if string is valid (not empty, valid size)
        head = createNode(splitString[0]);
        createTree(splitString);
        return head;
    }

    /**
     * TODO: Will check validity of the structure of a string
     * @param test is the String array to check
     * @return true if the String is valid, false if invalid
     */
    private Boolean checkValid(String[] test) {
        return true;
    }

    /**
     * Creates CommandNode object based on the content of the string
     * @param string string argument
     * @return CommandNode object containing the Command or argument
     */
    private CommandNode createNode(String string) {
        if (isCommand(string)) {
            return new CommandNode(commandCreator.createCommand(string));
        }
        return new CommandNode(Integer.parseInt(string));
    }

    /**
     * Checks to determine if the given string is a command or argument
     * @param string is the argument
     * @return true if the string is a Command
     */
    private Boolean isCommand(String string) {
        return !string.matches("\\d+");
    }

    /**
     * Creates the tree of CommandNode objects based on the original head
     * @param s is the String array of commands
     */
    private void createTree(String[] s) {
        CommandNode current = head;
        for (int i = 1; i < s.length; i++) {
            CommandNode temp = createNode(s[i]);
            current.getChildren().add(temp);
            if (isCommand(s[i])) current = temp;
        }
    }


    public static void main(String[] args){
        Parser p = new Parser();
        try {
            p.parse("fd 50");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
