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
        commandType = CommandType.HEAD;
        ready = false;
        traversed = false;
        parent = null;
    }

    @Override
    public Boolean isCommand() {
        return false;
    }

    @Override
    public void injectArguments() {}


    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public double getValue() {
        return 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.HEAD;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Commandable getCommand() {
        return null;
    }

    @Override
    public ArrayList<SyntaxNode> getChildren() {
        return this.children;
    }

    @Override
    public void clearCommand() {
    }
}
