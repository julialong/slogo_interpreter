package parser.unbundler;

import commands.Command;
import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

import java.util.ArrayList;
import java.util.List;


public class DoTimesUnbundler extends ControlUnbundler{

    private String variable;
    private double end;

    private ArrayList<String> unbundledArray;
    private CommandFactory commandFactory;
    
    /**
     * Creates an unbundler for the dotimes command
     */
    public DoTimesUnbundler(CommandFactory cf) {
        commandFactory = cf;
    }

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp) {
        int[] commandIndex = findBrackets(exp);
        setNumbers(exp, commandIndex[0] - 1);
        buildCommand(exp, commandIndex[0], commandIndex[1]);
        modifyList(exp, commandIndex[1]);
        return String.join(" ", unbundledArray);
    }

    /**
     * Sets the given parameters based on the entries in the first set of brackets
     * @param exp is is the entire ArrayList of the input commands
     */
    private void setNumbers(List<String> exp, int stopIndex) {
        variable = exp.get(0);
        try {
            end = Double.parseDouble(exp.get(1));
        }
        catch (Exception e) {
            end = evaluateExpression(exp, stopIndex);
        }
    }

   
    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    private void buildCommand(List<String> exp, int startIndex, int stopIndex) {
        unbundledArray = new ArrayList<>();
        for (double i = 1; i < end + 1; i++) {
            for (int j = startIndex + 1; j < stopIndex; j++)
                unbundledArray.add(replaceVariable(exp.get(j), i));
        }
    }

    /**
     * Builds and executes the expression to be evaluated
     *
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private double evaluateExpression(List<String> exp, int end) {
        List<String> expression = new ArrayList<>();
        for (int i = 1; i < end; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
        return executeExpression(expression);
    }

    /**
     *
     * @param expression is the List of the expression to execute
     * @return the value of the expression
     */
    private double executeExpression(List<String> expression) {
        double answer = 0;
        if (expression.size() > 0){
            answer = new Parser(commandFactory).parse(String.join(" ", expression));
        }
        return answer;
    }

    /**
     * Replaces the variable with the value of the current index
     * @param current is the current string
     * @param currentIndex is the value of the current position that needs to replace the variable
     * @return
     */
    private String replaceVariable(String current, double currentIndex) {
        if (current.equals(variable)) {
            return Double.toString(currentIndex);
        }
        else return current;
    }
}
