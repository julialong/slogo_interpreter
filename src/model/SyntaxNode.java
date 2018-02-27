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
    private ArrayList<SyntaxNode> children;


    public SyntaxNode() {
    }

    /**
     * Checks to see if the current node is a command
     * @return true if the current node is a command
     */
    public Boolean isCommand() {
        return false;
    }

    /**
     * Injects the child arguments into the current command so that the command object can execute
     */
    public void injectArguments() {
        if (this.hasChildren() && this.isCommand()) {
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
    public abstract ArrayList<SyntaxNode> getChildren();

    /**
     * Traverses to the next child of the current node that has not been executed
     * @return the next SyntaxNode child of the current Node
     */
    private SyntaxNode getNextChild() {
        for (SyntaxNode child : this.getChildren()) {
            if (!child.isReady()) {
                return child;
            }
        }
        return null;
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

    /**
     * Keeps track of the current state of the node
     * @return true if the node has been executed and is ready to be moved on from, false if needs more actions
     */
    public abstract boolean isReady();

    /**
     * Sets the current command to its initial state; as if it had not been executed
     */
    public abstract void clearCommand();

    /**
     * Gets the value from the node
     * @return double value of current node
     */
    public abstract double getValue();

    /**
     * @return true if current node is the head of the tree
     */
    private boolean isHead() {
        return false;
    }

    /**
     * Determines if the current node has children
     * @return true if the current node has children, false otherwise
     */
    public boolean hasChildren() {
        return this.getChildren().size() > 0;
    }

    /**
     * Determines if the current node has enough arguments to be evaluated
     * @return true if the current command is ready to execute, false otherwise
     */
    public abstract boolean isDone();

    /**
     * Gets the command contained in the current node
     * @return the command contained in the node, null if node is not a CommandableNode
     */
    public abstract Commandable getCommand();

    /**
     * Gets the type of node
     * @return the type of node
     */
    public abstract CommandType getCommandType();

    @Override
    public String toString() {
        return "type:" + this.getCommandType().toString() + " | children: " + this.getChildren().size();
    }

    /**
     * TODO: Make iterator traverse tree and return commands ready to execute
     * @return SyntaxNode iterator
     */
    @Override
    public Iterator iterator() {
        SyntaxNode commandTree = this;
        traverseToBottom(commandTree);

        return new Iterator<Commandable>() {
            SyntaxNode current = commandTree; // current is now equal to the bottom leftmost component of tree

            /**
             * Checks parent and children of the current node to see if the tree can be further parsed
             * @return true if tree can be further parsed
             */
            public boolean hasNext() {
                for (SyntaxNode child : current.getChildren()) {
                    if (!child.isReady() && child.isCommand()) {
                        return true;
                    }
                }
                if (current.getParent() != null && !current.getParent().isReady()) {
                    return true;
                }
                return false;
            }

            /**
             * Returns the next ready command object in the tree
             * @return next SyntaxNode object in the tree
             */
            public Commandable next() {
                while (current != null && (!current.isCommand() || !current.isHead() || current.isDone())) {
                    current = current.getParent();
                }
                if (current != null) {
                    current.injectArguments();
                }
                while (current != null && !current.isReady()) {
                    current = current.getNextChild();
                    if (current != null) {
                        current.injectArguments();
                    }
                }
                if (current != null) {
                    return current.getCommand();
                }
                return null;
            }
        };
    }

    /**
     * traverses to the bottom of the tree and sets current to the bottom leftmost node
     * @param current head of the current tree to be traversed
     */
    private void traverseToBottom(SyntaxNode current) {
        while (current != null && current.hasChildren()) {
            current = current.getNextChild();
        }
    }
}
