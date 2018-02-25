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
        super();
        command = newCommand;
        completed = 0;
    }

    public void resetChildren() {
        SyntaxNode current = this;

    }

    @Override
    public boolean isReady() {
        return (completed == this.getChildren().get(0).getValue());
    }

}
