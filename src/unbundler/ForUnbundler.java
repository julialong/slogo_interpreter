package unbundler;

import parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ForUnbundler implements  Unbundler{

    private Parser pr;

    private double repeat;

    private double variable;
    private double start;
    private double end;
    private double increment;
    private double repCount;

    private StringBuilder expression;
    private ArrayList<String> unbundledArray;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    public ForUnbundler() {
        repCount = 0;
    }

    /**
     * Creates an unbundler for the repeat command
     * @param p parser p
     */
    public ForUnbundler(Parser p) {
        pr = p;
        repCount = 0;
    }

    public String unbundle(List<String> exp, int index) {
        setNumbers(exp, index + 1);
        int[] commandIndex = findBrackets(exp, index + 5);
        return "";
    }

    private void setNumbers(List<String> exp, int index) {
        variable = Double.parseDouble(exp.get(index + 1));
        start = Double.parseDouble(exp.get(index + 2));
        end = Double.parseDouble(exp.get(index + 3));
        increment = Double.parseDouble(exp.get(index + 4));
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


}
