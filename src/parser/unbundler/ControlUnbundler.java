package parser.unbundler;

import commands.CommandFactory;
import parser.Parser;

import java.util.List;
import java.util.Stack;

abstract class ControlUnbundler implements Unbundler{

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Finds the beginning and ending brackets for the given control command
     * @param exp
     * @return
     */
    int[] findBrackets(List<String> exp, int start) {
        int[] answer = new int[2];
        boolean valid = false;
        Stack<Integer> bracketIndex = new Stack<>();
        for (int i = start; i < exp.size(); i++) {
            if (!notLeftBracket(exp.get(i))) {
                bracketIndex.push(i);
            }
            else if (!notRightBracket(exp.get(i)) && bracketIndex.size() > 0) {
                answer[1] = i;
                answer[0] = bracketIndex.pop();
                valid = true;
            }
            if (valid && bracketIndex.size() == 0) {
                return answer;
            }
        }
        return answer;
    }

    int[] findBrackets(List<String> exp) {
        return findBrackets(exp, 0);
    }

    /**
     * Modifies the list and returns a new list without the extracted, unbundled string
     * @param exp is the entire ArrayList of the input commands
     * @param lastIndex is the index where the command ends
     */
    protected void modifyList (List < String > exp, int lastIndex) {
        for (int i = lastIndex; i >= 0; i--) {
            exp.remove(i);
        }
    }

    protected void executeExpression(List<String> expression, boolean executeCommands, CommandFactory commandFactory) {
        if (expression.size() <= 0) {
            executeCommands = false;
        } else {
            double answer = new Parser(commandFactory).parse(String.join(" ", expression));
            executeCommands = (answer != 0);
        }
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a left bracket, false otherwise
     */
    protected boolean notLeftBracket (String current){
        return !current.equals(LEFT_BRACE);
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a right bracket, false otherwise
     */
    protected boolean notRightBracket (String current){
        return !current.equals(RIGHT_BRACE);
    }
}
