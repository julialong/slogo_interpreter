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

        children = new ArrayList<>();
        value = argument;
        ready = true;
        traversed = true;
        commandType = CommandType.ARGUMENT;
    }
}
