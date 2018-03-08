package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import view.Visualizer;

public class For extends ControlUnbundler {
	
	private static final int NUM_ARGS = 2;
	
    public For(Visualizer vis, Parser p) {
		super(vis, NUM_ARGS, p);
	}

	private String variable;
    private double start;
    private double end;
    private double increment;

    private ArrayList<String> unbundledArray;

    /**
     * Unbun
     * @param exp
     * @return
     */
    @Override
    protected String unbundle(List<String> exp) {
        setNumbers(exp, 1);
        int[] commandIndex = findBrackets(exp, 1);
        buildCommand(exp, commandIndex[0], commandIndex[1]);
        modifyList(exp, commandIndex[1]);
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

}
