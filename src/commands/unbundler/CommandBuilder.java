package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

public interface CommandBuilder {

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    default List<String> buildCommand(List<String> exp, String variable, double repeat, int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        if (executeCommands) {
            return buildArray(exp, variable, repeat, startIndexTrue, stopIndexTrue);
        } else {
            return buildArray(exp, variable, repeat, startIndexFalse, stopIndexFalse);
        }
    }

    /**
     * Builds a List from the given List
     * @param exp is the entire ArrayList of the input commands
     * @param variable is the variable that needs to be replaced
     * @param startIndex is the index to start at
     * @param stopIndex is the index to stop at
     * @return a correctly formed List
     */
    default List<String> buildArray(List<String> exp, String variable, double repeat, int startIndex, int stopIndex) {
        List <String> unbundledArray = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            for (int j = startIndex + 1; j < stopIndex; j++) {
                unbundledArray.add(replaceVariable(variable, exp.get(j), j));
            }
        }
        return unbundledArray;
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    default List<String> buildCommand(List<String> exp, int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        return buildCommand(exp, null, 1, startIndexTrue, stopIndexTrue, startIndexFalse, stopIndexFalse, executeCommands);
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    default List<String> buildCommand(List<String> exp, String variable, double repeat, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, variable, repeat, startIndex, stopIndex, 0, 0, executeCommands);
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    default List<String> buildCommand(List<String> exp, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, null, 1, startIndex, stopIndex, 0, 0, executeCommands);
    }

    /**
     * Builds an unbundled command that repeats the correct number of times
     * @param exp is the entire ArrayList of the input commands
     * @param startIndex is the index where the commands start
     * @param stopIndex is the index where the commands stop
     * @return the modified list
     */
    default List<String> buildCommand(List<String> exp, String variable, double repeat, int startIndex, int stopIndex) {
        return buildCommand(exp, variable, repeat, startIndex, stopIndex, true);
    }

    /**
     * Builds an unbundled command that repeats the correct number of times
     * @param exp is the entire ArrayList of the input commands
     * @param startIndex is the index where the commands start
     * @param stopIndex is the index where the commands stop
     * @return the modified list
     */
    default List<String> buildCommand(List<String> exp, int startIndex, int stopIndex) {
        return buildCommand(exp, startIndex, stopIndex, true);
    }

    /**
     * Replaces the variable with the value of the current index
     *
     * @param current      is the current string
     * @param currentIndex is the value of the current position that needs to replace the variable
     * @return
     */
    default String replaceVariable(String variable, String current, double currentIndex) {
        if (variable != null && current.equals(variable)) {
            return Double.toString(currentIndex);
        } else {
            return current;
        }
    }
}
