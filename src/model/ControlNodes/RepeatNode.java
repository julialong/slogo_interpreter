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

    public void setExpression(double arg) {
        expression = arg;
    }

    private boolean checkChildren() {
        if (expression == completed) {
            return true;
        }
        for (SyntaxNode child : this.getChildren()) {
            if (!child.isReady()) {
                return false;
            }
        }
        this.clearChildren();
        return false;
    }

    private void clearChildren() {
        for (int i = 1; i < this.getChildren().size(); i++) {

        }
    }

    private void clear (SyntaxNode node) {
        if(!node.hasChildren()) {
            node.clearCommand();
        }
    }

    @Override
    public boolean isReady() {
        return checkChildren();
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
    public ArrayList<SyntaxNode> getChildren() {
        return this.children;
    }

    @Override
    public double getValue() {
        return 0;
    }

    @Override
    public void clearCommand() {

    }

}
