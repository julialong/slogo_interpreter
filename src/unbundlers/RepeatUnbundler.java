package unbundlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Parser;

public class RepeatUnbundler {

    private Map<String, String> dictionary;
    private Parser pr;

    private double repeat;

    private StringBuilder expression;
    private ArrayList<String> unbundledArray;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Creates an unbundler for the repeat command
     * @param p parser p
     * @param variables Current dictionary of variables stored
     */
    public RepeatUnbundler(Parser p, Map<String,String> variables) {
        pr = p;
        dictionary = variables;
    }

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @param index is the index that the control command was found
     * @return the String of the unbundled control command
     */
    public String unbundle(ArrayList<String> exp, int index) {

        expression = new StringBuilder();
        int commandStartIndex = buildExpression(exp, index);
        repeat = pr.parse(expression.toString()).getValue();
        int commandEndIndex = buildCommand(exp, commandStartIndex);

        exp = modifyList(exp, commandStartIndex, commandEndIndex);

        return String.join(" ", unbundledArray);
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @param index index that the control command was found
     * @return the index of the first left bracket
     */
    private int buildExpression(ArrayList<String> exp, int index) {
        int i = index + 1;
        String current = exp.get(i);
        while (notLeftBracket(current) && i < exp.size()) {
            expression.append(addSpaces(current));
            i++;
            current = exp.get(i);
        }
        return i;
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @param commandIndex is the index where the command begins
     * @return the index where the command ends, or the last bracket
     */
    private int buildCommand(ArrayList<String> exp, int commandIndex) {
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
    private ArrayList<String> modifyList(ArrayList<String> exp, int firstIndex, int lastIndex) {
        List<String> start = exp.subList(0, firstIndex + 1);
        List<String> end = exp.subList(lastIndex - 1, exp.size()-1);
        start.addAll(end);

        return new ArrayList<> (start);
    }

    /**
     * Replaces the current string with a variable if the current string has been stored by the user as a variable
     * @param current is the current string
     * @return the string, replaced by the appropriate variable if necessary
     */
    private String replaceVariables(String current) {
        if (dictionary.containsKey(current)) {
            return dictionary.get(current);
        }
        else return current;
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
