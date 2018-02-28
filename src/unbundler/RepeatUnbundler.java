package unbundler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class RepeatUnbundler implements Unbundler {

    private double repeat;

    private List<String> expression;
    private ArrayList<String> unbundledArray;
    private CommandFactory commandFactory;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    public RepeatUnbundler() {
    }

    /**
     * Creates an unbundler for the repeat command
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
        int[] commandIndex = this.findBrackets(exp, index);
        expression = new ArrayList<>();
        buildExpression(exp, index, commandIndex[0]);
        executeExpression();
        buildCommand(exp, commandIndex[0], commandIndex[1]);
        modifyList(exp, index, commandIndex[1]);
        System.out.println("final expression:" + exp.toString());
        System.out.println("unbundled: " + unbundledArray.toString());
        return String.join(" ", unbundledArray);
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private void buildExpression(List<String> exp, int start, int end) {
        System.out.println(start + " " + end);
        for (int i = start + 1; i < end; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
        System.out.println("expression: " + expression.toString());
    }

    private void executeExpression() {
        if (expression.size() <= 0) {
            System.out.println("expression 0");
            repeat = 0;
        }
        else {
        Iterable<Commandable> iterable = new Parser(commandFactory).parse(String.join(" ", expression));
        for (Commandable c : iterable) {
            c.execute();
            repeat = c.getAns();
        }
        }
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    private void buildCommand(List<String> exp, int start, int stop) {
        unbundledArray = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            for (int j = start + 1; j < stop; j++)
                unbundledArray.add(exp.get(j));
        }
    }

    /**
     * Modifies the list and returns a new list without the extracted, unbundled string
     * @param exp is the entire ArrayList of the input commands
     * @param firstIndex is the index where the command begins
     * @param lastIndex is the index where the command ends
     */
    private void modifyList(List<String> exp, int firstIndex, int lastIndex) {
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            exp.remove(firstIndex);
        }
    }

    /**
     * Finds the beginning and ending brackets for the given control command
     * @param exp
     * @param index
     * @return
     */
    private int[] findBrackets(List<String> exp, int index) {
        int[] answer = new int[2];
        Stack<Integer> bracketIndex = new Stack<>();
        for (int i = index; i < exp.size(); i++) {
            if (!notLeftBracket(exp.get(i))) {
                bracketIndex.push(i);
            }
            else if (!notRightBracket(exp.get(i)) && bracketIndex.size() > 0) {
                answer[1] = i;
                answer[0] = bracketIndex.pop();
            }
        }
        return answer;
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

    public static void main (String[] args) {
        RepeatUnbundler r = new RepeatUnbundler();
        String p = "REPEAT random 10 [ lessp 8.0 5.1 ]";
        int[] a = r.findBrackets(new ArrayList<String>(Arrays.asList(p.split(" "))), 0);
        System.out.println(a[0] + " " + a[1]);
    }
}
