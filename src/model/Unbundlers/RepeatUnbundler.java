package model.Unbundlers;

import java.util.Map;
import model.Parser;

public class RepeatUnbundler {

    private Map<String, String> dictionary;
    private Parser pr;
    private StringBuilder unbundledString;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Creates an unbundler for the repeat command
     * @param p parser p
     * @param variables Current dictionary of variables stored
     * @param exp Array of the expression command(s)
     * @param command Array of the commands to repeat
     */
    public RepeatUnbundler(Parser p, Map<String,String> variables, String[] exp, String[] command) {
        pr = p;
        dictionary = variables;

        // this should parse and execute the command(s) in exp
        Double value = pr.parse(String.join(" ", exp)).getValue();

        unbundledString = new StringBuilder();
        for (int i = 0; i < value; i++) {
            for (int j = 0; j < command.length; j++) {
                System.out.println(command[j]);
                addString(command[j]);
            }
        }
    }

    /**
     * @return unbundled String
     */
    public String getUnbundledString() {
        return unbundledString.toString();
    }

    /**
     * Adds the string to the stringbuilder
     * @param current the current String that should be added
     */
    private void addString(String current) {
        if (!pr.isComment(current) && notBracket(current)) {
            current = replaceVariables(current);
            unbundledString.append(addSpaces(current));
        }
    }

    /**
     * Replaces the current string with a variable if the current string has been stored by the user as a variable
     * @param current is the current string
     * @return the string, replaced by the appropriate variable if necessary
     */
    private String replaceVariables(String current) {
        if (dictionary.containsKey(current)) {
            return dictionary.get(current);
        }
        else return current;
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a bracket, false otherwise
     */
    private boolean notBracket(String current) {
        return (!current.equals(LEFT_BRACE) || !current.equals(RIGHT_BRACE));
    }

    /**
     * @param noSpaces String that has currently been stripped of spaces
     * @return string with spaces added to the ends
     */
    private String addSpaces(String noSpaces) {
        return " " + noSpaces + " ";
    }
}
