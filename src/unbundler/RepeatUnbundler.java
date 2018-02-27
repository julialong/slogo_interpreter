package unbundler;

import java.util.ArrayList;
import java.util.List;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class RepeatUnbundler implements Unbundler {

    private double repeat;

    private StringBuilder expression;
    private ArrayList<String> unbundledArray;
    private CommandFactory commandFactory;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Creates an unbundler for the repeat command
     * @param p parser p
     * @param variables Current dictionary of variables stored
     */
    public RepeatUnbundler(CommandFactory cf) {
    		commandFactory = cf;
    }

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @param index is the index that the control command was found
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp, int index) {

        expression = new StringBuilder();
        int commandStartIndex = buildExpression(exp, index);
        executeExpression();
        int commandEndIndex = buildCommand(exp, index);
        exp = modifyList(exp, commandStartIndex, commandEndIndex);

        return String.join(" ", unbundledArray);
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @param index index that the control command was found
     * @return the index of the first left bracket
     */
    private int buildExpression(List<String> exp, int index) {
        int i = index + 1;
        String current = exp.get(i);
        while (notLeftBracket(current) && i < exp.size()) {
            expression.append(current);
            i++;
            current = exp.get(i);
        }
        return i;
    }

    private void executeExpression() {
        Iterable<Commandable> iterable = new Parser(commandFactory).parse(expression.toString());
        for (Commandable c : iterable) {
            c.execute();
            repeat = c.getAns();
        }
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @param commandIndex is the index where the command begins
     * @return the index where the command ends, or the last bracket
     */
    private int buildCommand(List<String> exp, int commandIndex) {
        unbundledArray = new ArrayList<>();
        int currentIndex = 0;
        for (int i = 0; i < repeat; i++) {
            currentIndex = commandIndex + 1;
            String current = exp.get(currentIndex);
            while (notRightBracket(current)) {
                unbundledArray.add(current);
                current = exp.get(currentIndex);
            }
        }
        return currentIndex;
    }

    /**
     * Modifies the list and returns a new list without the extracted, unbundled string
     * @param exp is the entire ArrayList of the input commands
     * @param firstIndex is the index where the command begins
     * @param lastIndex is the index where the command ends
     * @return modified list
     */
    private List<String> modifyList(List<String> exp, int firstIndex, int lastIndex) {
        List<String> start = exp.subList(0, firstIndex);
        List<String> end = exp.subList(lastIndex + 1, exp.size()-1);
        start.addAll(end);

        return new ArrayList<> (start);
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a left bracket, false otherwise
     */
    private boolean notLeftBracket(String current) {
        return !current.equals(LEFT_BRACE);
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a right bracket, false otherwise
     */
    private boolean notRightBracket(String current) {
        return !current.equals(RIGHT_BRACE);
    }

    /**
     * @param noSpaces String that has currently been stripped of spaces
     * @return string with spaces added to the ends
     */
    private String addSpaces(String noSpaces) {
        return " " + noSpaces + " ";
    }
}
