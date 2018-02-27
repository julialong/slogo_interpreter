package unbundler;

import parser.Parser;

import java.util.ArrayList;
import java.util.List;

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
        return "";
    }

    private void setNumbers(List<String> exp, int index) {
        variable = Double.parseDouble(exp.get(index + 1));
        start = Double.parseDouble(exp.get(index + 2));
        end = Double.parseDouble(exp.get(index + 3));
        increment = Double.parseDouble(exp.get(index + 4));
    }



}
