package model;

import commands.Commandable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author julia
 */
public abstract class SyntaxNode implements Iterable{


    private SyntaxNode parent;
    private Boolean traversed;


    SyntaxNode() {
    }

    /**
     * Checks to see if the current node is a command
     * @return true if the current node is a command
     */
    public Boolean isCommand() {
        return false;
    }

    private void injectArguments() {
        if (this.isCommand()) {
            for (SyntaxNode child : this.getChildren()) {
                if(child.isReady()) {
                    this.getCommand().inject(child.getValue());
                }
            }
        }
    }

    /**
     * gets the children for the given node
     * @return arraylist of child nodes
     */
    abstract ArrayList<SyntaxNode> getChildren();

    private SyntaxNode getNextChild() {
        for (SyntaxNode child : this.getChildren()) {
            if (!child.wasTraversed()) {
                return child;
            }
        }
        return null;
    }

    public boolean isReady() {
        return false;
    }

    /**
     * Sets the parent node for the given node
     * @param newParent is the SyntaxNode directly above the current Node
     */
    public void setParent(SyntaxNode newParent) {
        this.parent = newParent;
    }

    /**
     * Gets the parent node for the given node
     * @return the parent node
     */
    public SyntaxNode getParent(){
        return this.parent;
    }

    abstract double getValue();

    private boolean isHead() {
        return false;
    }

    abstract boolean wasTraversed();

    abstract Commandable getCommand();

    abstract CommandType getCommandType();

    @Override
    public String toString() {
        return "type:" + this.getCommandType().toString() + "| children: " + this.getChildren().size();
    }

    /**
     * TODO: Make iterator traverse tree and return commands ready to execute
     * @return SyntaxNode iterator
     */
    @Override
    public Iterator iterator() {
        SyntaxNode commandTree = this;
        traverseToBottom(commandTree);

        return new Iterator<SyntaxNode>() {
            SyntaxNode current = commandTree; // current is now equal to the bottom leftmost component of tree

            /**
             * Checks parent and children of the current node to see if the tree can be further parsed
             * @return true if tree can be further parsed
             */
            public boolean hasNext() {
                for (SyntaxNode child : current.getChildren()) {
                    if (!child.traversed) {
                        return true;
                    }
                }
                if (!current.isHead() || !current.getParent().traversed) {
                    return true;
                }
                return false;
            }

            /**
             * Returns the next ready command object in the tree
             * @return next SyntaxNode object in the tree
             */
            public SyntaxNode next() {
                System.out.println(current.toString());
                while (!current.isCommand() || current.traversed) {
                    current = current.getParent();
                }
                current.injectArguments();
                while (!current.isReady()) {
                    current = current.getNextChild();
                    current.injectArguments();
                }
                current.traversed = true;
                return current;
            }
        };
    }

    /**
     * traverses to the bottom of the tree and sets current to the bottom leftmost node
     * @param current head of the current tree to be traversed
     */
    private void traverseToBottom(SyntaxNode current) {
        int i = 0;
        while (current.getChildren().size() > 0) {
            System.out.println(i + " " + current.toString());
            current = current.getNextChild();
            i++;
        }
        System.out.println(i + " " + current.toString());
    }
}
