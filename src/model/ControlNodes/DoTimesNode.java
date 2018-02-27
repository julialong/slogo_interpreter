package model.ControlNodes;

import commands.Commandable;
import model.CommandType;
import model.ControlNodes.ControlNode;
import model.SyntaxNode;

import java.util.ArrayList;

public class DoTimesNode extends ControlNode {

    private ArrayList<SyntaxNode> children;
    private SyntaxNode parent;
    private Commandable command;
    private int completed;
    private int value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;


    DoTimesNode(Commandable newCommand) {
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
        return this.traversed;
    }

    @Override
    public Commandable getCommand() {
        return this.command;
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
        return this.value;
    }
}
