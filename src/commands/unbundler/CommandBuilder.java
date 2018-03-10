package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

public interface CommandBuilder {

    /**
     *
     * @param exp is the entire expression to evaluate in the fom of a List
     * @param variable is the variable name to replace
     * @param start is the number to start repetitions
     * @param end is the number to end repetitions
     * @param increment is the number to increment
     * @param startIndexTrue
     * @param stopIndexTrue
     * @param startIndexFalse
     * @param stopIndexFalse
     * @param executeCommands
     * @return
     */
    default List<String> buildCommand(List<String> exp, String variable, double start, double end, double increment,
                                      int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        if (executeCommands) {
            return buildArray(exp, variable, start, end, increment, startIndexTrue, stopIndexTrue);
        } else {
            return buildArray(exp, variable, start, end, increment, startIndexFalse, stopIndexFalse);
        }
    }

    default List<String> buildCommand(List<String> exp, String variable, double repeat, int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        return buildCommand(exp, variable, 1, repeat, 1, startIndexTrue, stopIndexTrue, startIndexFalse, stopIndexFalse, executeCommands);
    }

    default List<String> buildArray(List<String> exp, String variable, double start, double end, double increment, int startIndex, int stopIndex) {
        List <String> unbundledArray = new ArrayList<>();
        for (double i = start; i < end; i += increment) {
            for (int j = startIndex + 1; j < stopIndex; j++) {
                unbundledArray.add(replaceVariable(variable, exp.get(j), j));
            }
        }
        return unbundledArray;
    }

    default List<String> buildCommand(List<String> exp, int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        return buildCommand(exp, null, 1, startIndexTrue, stopIndexTrue, startIndexFalse, stopIndexFalse, executeCommands);
    }

    default List<String> buildCommand(List<String> exp, String variable, double repeat, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, variable, repeat, startIndex, stopIndex, 0, 0, executeCommands);
    }

    default List<String> buildCommand(List<String> exp, double repeat, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, null, repeat, startIndex, stopIndex, 0, 0, executeCommands);
    }

    default List<String> buildCommand(List<String> exp, String variable, double repeat, int startIndex, int stopIndex) {
        return buildCommand(exp, variable, repeat, startIndex, stopIndex, true);
    }

    default List<String> buildCommand(List<String> exp, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, 1, startIndex, stopIndex, executeCommands);
    }

    private String replaceVariable(String variable, String current, double currentIndex) {
        if (variable != null && current.equals(variable)) {
            return Double.toString(currentIndex);
        } else {
            return current;
        }
    }
}
