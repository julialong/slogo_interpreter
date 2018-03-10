package commands.unbundler.conditional_unbundlers;

import commands.VariableReplacer;
import commands.unbundler.ControlUnbundler;
import parser.Parser;
import view.Visualizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Unbundler for conditional control commands
 * @author julialong
 */
abstract class ConditionalUnbundler extends ControlUnbundler {

    /**
     * Creates a new ConditionalUnbundler
     * @param vis is the current Visualizer
     * @param variableReplacer is the current variableReplacer
     * @param numArgs is the number of arguments
     * @param parser is the current parser
     */
    ConditionalUnbundler(Visualizer vis, VariableReplacer variableReplacer, int numArgs, Parser parser) {
        super(vis, variableReplacer, numArgs, parser);
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
}
