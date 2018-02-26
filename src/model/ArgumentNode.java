package model;

import commands.Commandable;

import java.util.ArrayList;

public class ArgumentNode extends SyntaxNode {

    private ArrayList<SyntaxNode> children;
    private int currentChild;
    private SyntaxNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    ArgumentNode(Double argument) {
        super();
        children = new ArrayList<>();
        value = argument;
        ready = true;
        traversed = true;
    }

    @Override
    CommandType getCommandType() {
        return CommandType.ARGUMENT;
    }

    @Override
    public boolean wasTraversed() {
        return this.traversed;
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
    public double getValue() {
        return this.value;
    }
}
