package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * Abstract class that implements methods useful for classes that implement on a certain condition
 * @author julialong
 */
abstract class ConditionalUnbundler extends ControlUnbundler{

    /**
     * Creates a ConditionalUnbundler object
     * @param visualizer is the current Visualizer being used in the session
     * @param variableReplacer is the current variableReplacer object
     * @param numArgs is the number of arguments needed
     * @param parser is the current Parser object
     */
    ConditionalUnbundler(Visualizer visualizer, VariableReplacer variableReplacer, int numArgs, Parser parser) {
        super(visualizer, variableReplacer, numArgs, parser);
    }

    /**
     * Executes the expression and evaluates if the returned value is nonzero
     * @param expression is the list of the expression to evaluate
     * @return true if the expression is not equal to zero, false otherwise
     */
    boolean executeExpression(List<String> expression) {
        boolean executeCommands;
        if (expression.size() <= 0) {
            executeCommands = false;
        } else {
            double answer = this.getParser().parse(String.join(" ", expression));
            executeCommands = (answer > 0 || answer < 0);
        }
        return executeCommands;
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    List<String> buildExpression(List<String> exp, int end) {
        List<String> expression = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
        return expression;
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    List<String> buildCommand(List<String> exp, int startIndexTrue, int stopIndexTrue, int startIndexFalse, int stopIndexFalse, boolean executeCommands) {
        List <String> unbundledArray = new ArrayList<>();
        if (executeCommands) {
            for (int j = startIndexTrue + 1; j < stopIndexTrue; j++) {
                unbundledArray.add(exp.get(j));
            }
        } else {
            for (int j = startIndexFalse + 1; j < stopIndexFalse; j++) {
                unbundledArray.add(exp.get(j));
            }
        }
        return unbundledArray;
    }

    /**
     * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
     * @param exp is the entire ArrayList of the input commands
     * @return the index where the command ends, or the last bracket
     */
    List<String> buildCommand(List<String> exp, int startIndex, int stopIndex, boolean executeCommands) {
        return buildCommand(exp, startIndex, stopIndex, 0, 0, executeCommands);
    }

}
