package model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import commands.Commandable;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandNode implements Iterable{

    private ArrayList<CommandNode> children;
    private CommandNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private int totalArguments;
    private CommandType commandType;

    /**
     * Creates new CommandNode if the argument passed in implements the Commandable interface
     * @param newCommand the Command object to be made into a node
     */
    public CommandNode(Commandable newCommand) {
        children = new ArrayList<>();
        command = newCommand;
        commandType = CommandType.COMMAND;
        ready = false;
    }

    /**
     * Creates new CommandNode if the argument passed in is a number
     * @param argument the number to be made into a node
     */
    public CommandNode(double argument) {
        commandType = CommandType.ARGUMENT;
        value = argument;
    }

    /**
     * Checks to see if the current node is a command
     * @return true if the current node is a command
     */
    public Boolean isCommand() {
        return (this.commandType == CommandType.COMMAND);
    }

    /**
     * gets the children for the given node
     * @return arraylist of child nodes
     */
    public ArrayList<CommandNode> getChildren() {
        return this.children;
    }

    /**
     * Compares the current number of children of the node to the needed children for the node
     * @return the state of the node's children
     */
    public Boolean checkChildren() {
//        Boolean state = (this.getChildren().size() == this.command.getNumChildren());
//        this.ready = state;
//        return state;
        return true;
    }

    /**
     * Sets the parent node for the given node
     * @param newParent is the CommandNode directly above the current Node
     */
    public void setParent(CommandNode newParent) {
        this.parent = newParent;
    }

    /**
     * Gets the parent node for the given node
     * @return the parent node
     */
    public CommandNode getParent() {
        return parent;
}

    /**
     * TODO: Make iterator traverse tree and return commands ready to execute
     * @return CommandNode iterator
     */
    @Override
    public Iterator iterator() {
        CommandNode commandTree = this;
        return new Iterator<CommandNode>() {

            public boolean hasNext() {
                return false;
            }

            public CommandNode next() {
                return new CommandNode(0);
            }
        };
    }
}
