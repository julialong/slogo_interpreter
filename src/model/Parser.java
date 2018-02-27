package model;

import commands.CommandFactory;

import java.util.Map;

/**
 *
 * @author julia
 */
public class Parser {

    private CommandFactory commandCreator;
    private SyntaxNode head;

    private Map<String, String> variableMap;
    private Map<String, String> commandMap;

    public Parser(CommandFactory cf) {
        commandCreator = cf;

    }

    /**
     * Parses a string and builds a tree of CommandNodes
     * @param s is the input string
     * @return the head node of a tree of CommandNodes
     */
    public SyntaxNode parse(String s) {
        String[] splitString = s.split(" ");
        checkValid(splitString);
        return parseCommands(splitString);
    }

    private SyntaxNode parseCommands(String[] s) {
        head = new HeadNode();
        createTree(s);
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
     * Creates SyntaxNode object based on the content of the string
     * @param string string argument
     * @return SyntaxNode object containing the Command or argument
     */
    private SyntaxNode createNode(String string) {
        if (isCommand(string)) {
            return new CommandableNode(commandCreator.createCommand(string));
        }
        return new ArgumentNode(Double.parseDouble(string));
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
     * Checks for list structure required for control command
     * @param string is the argument
     * @return true if the string is a control argument
     */
    private Boolean isControl(String string) {
        return (string.contains("[") && string.contains("]"));
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
     * Creates the tree of SyntaxNode objects based on the original head
     * TODO: check for incomplete or invalid tree and throw appropriate exception
     * TODO: refactor some of this logic to SyntaxNode class to improve encapsulation
     * @param s is the String array of commands
     */
    private void createTree(String[] s) {
        SyntaxNode current = head;
        for (int i = 0; i < s.length; i++) {
            SyntaxNode temp = createNode(s[i]);
            System.out.println(current.toString() + " + " + temp.toString());
            current.getChildren().add(temp);
            temp.setParent(current);
            if (temp.isCommand()) {
                current = temp;
            }
            else if (!temp.isCommand()) {
                temp.getParent().getCommand().inject(temp.getValue());
                current = temp.getParent();
                while (current.getParent().isDone()) {
                    current = current.getParent();
                }

            }
        }
    }
}
