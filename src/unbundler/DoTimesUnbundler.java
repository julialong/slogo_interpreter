package unbundler;

import parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoTimesUnbundler implements Unbundler{

    private Parser pr;

    private double repeat;

    private StringBuilder expression;
    private ArrayList<String> unbundledArray;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Creates an unbundler for the dotimes command
     * @param p parser p
     */
    public DoTimesUnbundler(Parser p) {
        pr = p;
    }

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @param index is the index that the control command was found
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp, int index) {


        return "";
    }

}
