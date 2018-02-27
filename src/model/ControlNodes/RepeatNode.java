package model.ControlNodes;

import commands.Commandable;
import model.CommandType;
import model.CommandableNode;
import model.ControlNodes.ControlNode;
import model.SyntaxNode;

import java.util.ArrayList;

public class RepeatNode extends ControlNode {

    private ArrayList<SyntaxNode> children;
    private SyntaxNode parent;
    private Commandable command;
    private int completed;
    private double expression;
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

    @Override
    public boolean isReady() {
        return (completed == this.getChildren().get(0).getValue());
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CONTROL;
    }

    @Override
    public boolean isDone() {
        for (SyntaxNode child : this.getChildren()) {
            if (!child.isDone()) {
                return false;
            }
        }
        // TODO: check if has enough children
        return true;
    }

    @Override
    public Commandable getCommand() {
        return null;
    }

    @Override
    public void setDone() {

    }

    @Override
    public ArrayList<SyntaxNode> getChildren() {
        return this.children;
    }

    @Override
    public double getValue() {
        return 0;
    }
}
