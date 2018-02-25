package model;

import commands.Commandable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author julia
 */
public class CommandNode implements Iterable{

    private ArrayList<CommandNode> children;
    private int currentChild;
    private CommandNode parent;
    private Commandable command;
    private double value;
    private Boolean ready;
    private Boolean traversed;
    private int totalArguments;
    private CommandType commandType;

    /**
     * Creates new CommandNode if the argument passed in implements the Commandable interface
     * @param newCommand the Command object to be made into a node
     */
    CommandNode(Commandable newCommand) {
        children = new ArrayList<>();
        currentChild = 0;
        command = newCommand;
        commandType = CommandType.COMMAND;
        ready = false;
        traversed = false;
    }

    /**
     * Creates new CommandNode if the argument passed in is a number
     * @param argument the number to be made into a node
     */
    CommandNode(double argument) {
        commandType = CommandType.ARGUMENT;
        value = argument;
        children = new ArrayList<>();
        currentChild = 0;
        traversed = false;
    }

    /**
     * Checks to see if the current node is a command
     * @return true if the current node is a command
     */
    public Boolean isCommand() {
        return (this.commandType == CommandType.COMMAND);
    }

    private void injectArguments() {
        if (this.isCommand()) {
            for (CommandNode child : this.children) {
                this.command.inject(child.value);
                child.traversed = true;
            }
        }
    }

    /**
     * gets the children for the given node
     * @return arraylist of child nodes
     */
    public ArrayList<CommandNode> getChildren() {
        return this.children;
    }

    private CommandNode getNextChild() {
        this.currentChild++;
        return this.getChildren().get(currentChild - 1);
    }

    /**
     * Compares the current number of children of the node to the needed children for the node
     * @return the state of the node's children
     */
    public Boolean checkChildren() {
//        Boolean state = (this.getChildren().size() == this.command.getNumChildren());
//        this.ready = state;
//        return state;
        return true;
    }

    /**
     * Sets the parent node for the given node
     * @param newParent is the CommandNode directly above the current Node
     */
    public void setParent(CommandNode newParent) {
        this.parent = newParent;
    }

    /**
     * Gets the parent node for the given node
     * @return the parent node
     */
    public CommandNode getParent(){
        return this.parent;
    }

    private boolean isHead() {
        return false;
    }

    /**
     * TODO: Make iterator traverse tree and return commands ready to execute
     * @return CommandNode iterator
     */
    @Override
    public Iterator iterator() {
        CommandNode commandTree = this;
        traverseToBottom(commandTree);

        return new Iterator<CommandNode>() {
            CommandNode current = commandTree; // current is now equal to the bottom leftmost component of tree

            /**
             * Checks parent and children of the current node to see if the tree can be further parsed
             * @return true if tree can be further parsed
             */
            public boolean hasNext() {
                if (!current.getParent().traversed) {
                    return true;
                }
                for (CommandNode child : current.getChildren()) {
                    if (!child.traversed) {
                        return true;
                    }
                }
                return false;
            }

            public CommandNode next() {
                //current.value = current.command.
                while (!current.isCommand()) {
                    current = current.getParent();
                    if (!current.ready) {

                    }
                }
                current.injectArguments();
                return current;
            }
        };
    }

    /**
     * traverses to the bottom of the tree and sets current to the bottom leftmost node
     * @param current head of the current tree to be traversed
     */
    private void traverseToBottom(CommandNode current) {
        while (current.getChildren().size() > 0) {
            current = current.getNextChild();
        }
    }
}
