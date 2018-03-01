package unbundler;

import java.util.ArrayList;
import java.util.List;

public class DoTimesUnbundler extends ControlUnbundler {

    private String variable;
    private double end;

    private ArrayList<String> unbundledArray;

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @param index is the index that the control command was found
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp, int index) {
        setNumbers(exp, index + 2);
        int[] commandIndex = findBrackets(exp, index + 4);
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
        end = Double.parseDouble(exp.get(index + 1));
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

    private String replaceVariable(String current, double currentIndex) {
        if (current.equals(variable)) {
            return Double.toString(currentIndex);
        }
        else return current;
    }
}
