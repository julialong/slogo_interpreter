package model;

import commands.Commandable;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandNode implements Iterable{

    private ArrayList<CommandNode> children;
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
     * gets the children for the given node
     * @return
     */
    public ArrayList<CommandNode> getChildren() {
        return this.children;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
