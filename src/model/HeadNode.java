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
        commandType = CommandType.COMMAND;
        ready = false;
        traversed = false;
        parent = null;
    }

}
