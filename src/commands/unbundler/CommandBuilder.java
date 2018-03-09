package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

public interface CommandBuilder {

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    default List<String> buildCommand(List<String> exp, int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        List <String> unbundledArray = new ArrayList<>();
        if (executeCommands) {
            for (int j = startIndexTrue + 1; j < stopIndexTrue; j++) {
                unbundledArray.add(exp.get(j));
            }
        } else {
            for (int i = startIndexFalse + 1; i < stopIndexFalse; i++) {
                unbundledArray.add(exp.get(i));
            }
        }
        return unbundledArray;
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    default List<String> buildCommand(List<String> exp, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, startIndex, stopIndex, 0, 0, executeCommands);
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

}
