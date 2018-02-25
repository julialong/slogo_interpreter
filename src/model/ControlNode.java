package model;

import commands.Commandable;

import java.util.ArrayList;

public abstract class ControlNode extends SyntaxNode{

    private ArrayList<SyntaxNode> children;
    private int currentChild;
    private SyntaxNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    ControlNode() {
        children = new ArrayList<>();
        currentChild = 0;
        commandType = CommandType.CONTROL;
        ready = false;
        traversed = false;
    }

}
