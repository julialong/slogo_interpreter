package commands.unbundler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.BracketFinder;
import commands.ListModifier;
import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * Abstract class used for all unbundler classes
 * @author benhubsch, julialong
 */
public abstract class ControlUnbundler extends NonUpdatableCommand implements ListModifier, BracketFinder, CommandBuilder {
	
	private Parser parser;

	/**C
	 * Creates a new ControlUnbundler class
	 * @param vis is the current Visualizer class
	 * @param numArgs is the number of arguments
	 * @param parser is the current Parser class
	 */
	public ControlUnbundler(Visualizer vis, VariableReplacer var_replacer, int numArgs, Parser parser) {
		super(vis, var_replacer, numArgs);
		this.parser = parser;
	}

	/**
	 * Converts the current list of arguments to a list of expressions
	 * @param args is the list of arguments
	 * @return List of expressions
	 */
	List<String> argsToExp(List<String> args) {
		List<String> exp = new ArrayList<>();
		for (String arg : args) {
			exp.addAll(Arrays.asList(arg.split(" ")));
		}
		
		return exp;
	}

	/**
	 * Returns the current parser for this class
	 * @return the current parser
	 */
	protected Parser getParser() {
		return this.parser;
	}

	/**
	 * Parses the value of the unbundled expression and returns the value
	 * @param args is the list of arguments
	 * @return the double value of the expression
	 */
	@Override
	protected double calcValue(List<String> args) {
		String unbundled = unbundle(argsToExp(args));
		return parser.parse(unbundled);
	}

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    protected List<String> buildExpression(List<String> exp, int startIndex, int endIndex) {
        List<String> expression = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
        return expression;
    }


	/**
	 * Unbundles the current command so that it can be executed
	 * @param exp is the expression to evaluate as a list
	 * @return the unbundled String
	 */
	protected abstract String unbundle(List<String> exp);
}