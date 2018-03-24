package commands.unbundler.conditional_unbundlers;


import commands.factory.VariableReplacer;
import commands.unbundler.ControlUnbundler;
import parser.Parser;
import view.Visualizer;

import java.util.List;

/**
 * Unbundler for conditional control commands
 *
 * Similar to the ControlUnbundler class, this separate ConditionalUnbundler class prevents duplicated code in our
 * conditional subclasses by extracting similar methods to this level.
 *
 * Incorporating this abstract class as an extension of the ControlUnbundler is an example of implementing the Interface
 * Segregation Principle needed for SOLID code. By creating a more specific class for conditional commands only, we prevent
 * unnecessary code being present in the superclass.
 *
 * @author julialong
 */
abstract class ConditionalUnbundler extends ControlUnbundler {

    /**
     * Creates a new ConditionalUnbundler
     * @param visualizer is the current Visualizer
     * @param variableReplacer is the current variableReplacer
     * @param numArgs is the number of arguments
     * @param parser is the current parser
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
}
