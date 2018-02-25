package model;

import commands.Commandable;

import java.util.ArrayList;

public class RepeatNode extends ControlNode{

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

    @Override
    public boolean isReady() {
        return true;
    }

}
