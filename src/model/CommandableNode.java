package model;

import commands.Commandable;

import java.util.ArrayList;

public class CommandableNode extends SyntaxNode {

    private ArrayList<SyntaxNode> children;
    private int currentChild;
    private SyntaxNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    /**
     *
     * @param newCommand
     */
    CommandableNode(Commandable newCommand) {
        super();
        children = new ArrayList<>();
        currentChild = 0;
        command = newCommand;
        commandType = CommandType.COMMAND;
        ready = false;
        traversed = false;
    }

    /**
     * Checks to see if the current node is a command
     * @return true if the current node is a command
     */
    @Override
    public Boolean isCommand() {
        return true;
    }


    @Override
    public boolean isReady() {
        return this.ready;
    }

    @Override
    public double getValue() {
        return command.getAns();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.COMMAND;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Commandable getCommand() {
        return this.command;
    }

    @Override
    public ArrayList<SyntaxNode> getChildren() {
        return this.children;
    }

    @Override
    public void clearCommand() {
        this.ready = false;
    }
}
