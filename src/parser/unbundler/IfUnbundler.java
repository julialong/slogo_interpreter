package parser.unbundler;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class IfUnbundler extends ControlUnbundler{

    private List<String> expression;
    private ArrayList<String> unbundledArray;
    private CommandFactory commandFactory;

    private boolean executeCommands;

    /**
     * Creates an unbundler for the repeat command
     */
    public IfUnbundler(CommandFactory cf) {
        commandFactory = cf;
    }

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp) {
        int[] commandIndex = this.findBrackets(exp);
        expression = new ArrayList<>();
        buildExpression(exp, commandIndex[0]);
        executeExpression(expression, executeCommands, commandFactory);
        buildCommand(exp, commandIndex[0], commandIndex[1]);
        modifyList(exp, commandIndex[1]);
        return String.join(" ", unbundledArray);
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private void buildExpression(List<String> exp, int end) {
        for (int i = 1; i < end; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    private void buildCommand(List<String> exp, int start, int stop) {
        unbundledArray = new ArrayList<>();
        if (executeCommands) {
            for (int j = start + 1; j < stop; j++) {
                unbundledArray.add(exp.get(j));
            }
        }
        else {
            unbundledArray.add("#nocommands");
        }
    }
}
