package model;

import commands.CommandFactory;

import java.util.Map;

public class Parser {

    private CommandFactory commandCreator;
    private CommandNode head;

    private Map<String, Double> storedVariable;

    public Parser(CommandFactory cf) {
        commandCreator = cf;
    }

    /**
     * Parses a string and builds a tree of CommandNodes
     * @param s is the input string
     * @return the head node of a tree of CommandNodes
     */
    public CommandNode parse(String s) {
        String[] splitString = s.split(" ");

        try {
             // checkValid(splitString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
     * Checks to determine if the given string is a command
     * @param string is the argument
     * @return true if the string is a Command
     */
    private Boolean isCommand(String string) {
        return string.matches("[a-zA-Z_]+(\\?)?");
    }

    /**
     * Checks to determine if the given string is a number argument
     * @param string is the argument
     * @return true if the string is an argument
     */
    private Boolean isArgument(String string) {
        return string.matches("\t\n" + "-?[0-9]+\\.?[0-9]*");
    }

    /**
     * Checks to determine if the given string is a variable
     * @param string is the argument
     * @return true if the string is an argument
     */
    private Boolean isVariable(String string) {
        return string.matches(":[a-zA-Z_]+");
    }

    /**
     * Checks to determine if the given string is a comment
     * @param string is the argument
     * @return true if the string is an argument
     */
    private Boolean isComment(String string) {
        return string.matches("^#.*");
    }

    /**
     * Creates the tree of CommandNode objects based on the original head
     * TODO: check for incomplete or invalid tree and throw appropriate exception
     * @param s is the String array of commands
     */
    private void createTree(String[] s) {
        CommandNode current = head;
        for (int i = 1; i < s.length; i++) {
            CommandNode temp = createNode(s[i]);
            current.getChildren().add(temp);
            temp.setParent(current);
            if (temp.isCommand()) current = temp;
            else if (temp.isCommand() && temp.checkChildren()) {
                current = temp.getParent();
            }
        }
    }
}
