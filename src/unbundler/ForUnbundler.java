package unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ForUnbundler implements  Unbundler{


    private String variable;
    private double start;
    private double end;
    private double increment;

    private ArrayList<String> unbundledArray;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Creates an unbundler for the repeat command
     */
    public ForUnbundler() {
    }

    /**
     * Unbun
     * @param exp
     * @param index
     * @return
     */
    public String unbundle(List<String> exp, int index) {
        setNumbers(exp, index + 2);
        int[] commandIndex = findBrackets(exp, index + 6);
        System.out.println(index + " " + commandIndex[0] + " " + commandIndex[1]);
        buildCommand(exp, commandIndex[0], commandIndex[1]);
        modifyList(exp, index, commandIndex[1]);
        System.out.println("final expression:" + exp.toString());
        System.out.println("unbundled: " + unbundledArray.toString());
        return String.join(" ", unbundledArray);
    }

    /**
     * Sets the given parameters based on the entries in the first set of brackets
     * @param exp is is the entire ArrayList of the input commands
     * @param index is the index of the start of the expression
     */
    private void setNumbers(List<String> exp, int index) {
        variable = exp.get(index);
        start = Double.parseDouble(exp.get(index + 1));
        end = Double.parseDouble(exp.get(index + 2));
        increment = Double.parseDouble(exp.get(index + 3));
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    private void buildCommand(List<String> exp, int startIndex, int stopIndex) {
        unbundledArray = new ArrayList<>();
        for (double i = start; i < end; i+= increment) {
            for (int j = startIndex + 1; j < stopIndex; j++)
                unbundledArray.add(replaceVariable(exp.get(j), i));
        }
    }

    private String replaceVariable(String current, double currentIndex) {
        if (current.equals(variable)) {
            return Double.toString(currentIndex);
        }
        else return current;
    }

    /**
     * Modifies the list and returns a new list without the extracted, unbundled string
     * @param exp is the entire ArrayList of the input commands
     * @param firstIndex is the index where the command begins
     * @param lastIndex is the index where the command ends
     */
	private void modifyList(List<String> exp, int firstIndex, int lastIndex) {
		for (int i=lastIndex; i >= firstIndex; i--) {
			exp.remove(i);
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
>>>>>>> ben
     * @param current is the current string
     * @return true if the current string is not a right bracket, false otherwise
     */
    private boolean notRightBracket(String current) {
        return !current.equals(RIGHT_BRACE);
    }


}
