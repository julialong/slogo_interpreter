package parser.unbundler;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class IfElseUnbundler extends ControlUnbundler {

    private List<String> expression;
    private List<String> unbundledArray;
    private CommandFactory commandFactory;

    private boolean executeCommands;

    /**
     * Creates an unbundler for the repeat command
     */
    public IfElseUnbundler(CommandFactory cf) {
        commandFactory = cf;
    }

    /**
     * unbundles the given control command starting at index
     *
     * @param exp   is the entire ArrayList of the input commands
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp) {
        System.out.println("start unbundler here");
        int[] trueCommandIndex = findBrackets(exp);
        int[] falseCommandIndex = findBrackets(exp, trueCommandIndex[1]);
        expression = new ArrayList<>();
        buildExpression(exp, trueCommandIndex[0]);
        executeExpression(expression, executeCommands, commandFactory);
        unbundledArray = new ArrayList<>();
        buildCommand(exp, trueCommandIndex[0], trueCommandIndex[1], falseCommandIndex[0], falseCommandIndex[1]);
        modifyList(exp, falseCommandIndex[1]);
        return String.join(" ", unbundledArray);
    }

    /**
     * Builds the expression to be evaluated
     *
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
     *
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    private void buildCommand(List<String> exp, int startTrue, int stopTrue, int startFalse, int stopFalse) {
        if (executeCommands) {
            for (int j = startTrue + 1; j < stopTrue; j++) {
                unbundledArray.add(exp.get(j));
            }
        } else {
            for (int j = startFalse + 1; j < stopFalse; j++) {
                unbundledArray.add(exp.get(j));
            }
        }
    }
}
