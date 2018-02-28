package model;

import commands.Commandable;
import model.CommandType;
import model.ControlNode;
import model.SyntaxNode;

import java.util.ArrayList;

public class RepeatNode extends ControlNode {

    private ArrayList<SyntaxNode> children;
    private int currentChild;
    private SyntaxNode parent;
    private Commandable command;
    private int completed;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    RepeatNode(Commandable newCommand) {
        children = new ArrayList<>();
        command = newCommand;
        completed = 0;
        traversed = false;
    }

    public void resetChildren() {
        SyntaxNode current = this;

    }

    @Override
    public boolean isReady() {
        return (completed == this.getChildren().get(0).getValue());
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CONTROL;
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
    public void hasBeenTraversed() {

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