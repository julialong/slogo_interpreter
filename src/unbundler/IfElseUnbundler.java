package unbundler;

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
     * @param index is the index that the control command was found
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp, int index) {
        int[] trueCommandIndex = findBrackets(exp, index);
        int[] falseCommandIndex = findBrackets(exp, trueCommandIndex[1]);
        expression = new ArrayList<>();
        buildExpression(exp, index, trueCommandIndex[0]);
        executeExpression();
        unbundledArray = new ArrayList<>();
        buildCommand(exp, trueCommandIndex[0], trueCommandIndex[1], falseCommandIndex[0], falseCommandIndex[1]);
        modifyList(exp, index, falseCommandIndex[1]);
        return String.join(" ", unbundledArray);
    }

    /**
     * Builds the expression to be evaluated
     *
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private void buildExpression(List<String> exp, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
    }

    private void executeExpression() {
        if (expression.size() <= 0) {
            executeCommands = false;
        } else {
            Iterable<Commandable> iterable = new Parser(commandFactory).parse(String.join(" ", expression));
            for (Commandable c : iterable) {
                c.execute();
                executeCommands = (c.getAns() != 0);
            }
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
