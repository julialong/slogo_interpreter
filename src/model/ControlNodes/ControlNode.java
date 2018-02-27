package model.ControlNodes;

import commands.Commandable;
import model.CommandType;
import model.SyntaxNode;

import java.util.ArrayList;

public abstract class ControlNode extends SyntaxNode {

    private ArrayList<SyntaxNode> children;
    private SyntaxNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    ControlNode() {
        super();
        commandType = CommandType.CONTROL;
        ready = false;
        traversed = false;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CONTROL;
    }

    @Override
    public abstract boolean wasTraversed();

    @Override
    public abstract Commandable getCommand();

    @Override
    public abstract void hasBeenTraversed();
}
