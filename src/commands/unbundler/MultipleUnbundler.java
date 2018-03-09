package commands.unbundler;

import parser.Parser;
import view.Visualizer;


abstract class MultipleUnbundler extends ControlUnbundler{

    /**
     * Creates a new MultipleControlUnbundler class
     *
     * @param vis     is the current Visualizer class
     * @param numArgs is the number of arguments
     * @param parser  is the current Parser class
     */
    MultipleUnbundler(Visualizer vis, int numArgs, Parser parser) {
        super(vis, numArgs, parser);
    }

    /**
     * Replaces the current string with the current index, if the current string is the variable name
     *
     * @param variable is the string of the variable name
     * @param current is the current string we are parsing
     * @param currentIndex is the current number of the loop we are on
     * @return the modified string
     */
    String replaceVariable(String variable, String current, double currentIndex) {
        if (current.equals(variable)) {
            return Double.toString(currentIndex);
        } else {
            return current;
        }
    }

}
