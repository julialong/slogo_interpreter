package commands.unbundler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.BracketFinder;
import commands.ListModifier;
import commands.NonUpdatableCommand;
import parser.Parser;
import view.Visualizer;

/**
 * Abstract class used for all unbundler classes
 * @author benhubsch, julialong
 */
public abstract class ControlUnbundler extends NonUpdatableCommand implements ListModifier, BracketFinder {
	
	private Parser parser;

	/**
	 * Creates a new ControlUnbundler class
	 * @param vis is the current Visualizer class
	 * @param numArgs is the number of arguments
	 * @param parser is the current Parser class
	 */
	public ControlUnbundler(Visualizer vis, int numArgs, Parser parser) {
		super(vis, numArgs);
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
	 * Unbundles the current command so that it can be executed
	 * @param exp is the expression to evaluate as a list
	 * @return the unbundled String
	 */
	protected abstract String unbundle(List<String> exp);
}