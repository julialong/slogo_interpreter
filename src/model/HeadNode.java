package model;

import commands.Commandable;

import java.util.ArrayList;

public class HeadNode extends SyntaxNode{

    private ArrayList<SyntaxNode> children;
    private int currentChild;
    private SyntaxNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    HeadNode() {
        children = new ArrayList<>();
        currentChild = 0;
        commandType = CommandType.COMMAND;
        ready = false;
        traversed = false;
        parent = null;
    }

    @Override
    public Boolean isCommand() {
        return true;
    }

    @Override
    public boolean isReady() {
        return command.isReady();
    }

    @Override
    public double getValue() {
        return 0;
    }

    @Override
    CommandType getCommandType() {
        return CommandType.COMMAND;
    }

    @Override
    public boolean wasTraversed() {
        return this.traversed;
    }

    @Override
    public Commandable getCommand() {
        return this.command;
    }

    @Override
    public ArrayList<SyntaxNode> getChildren() {
        return this.children;
    }


}
