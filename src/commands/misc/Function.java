package commands.misc;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableCommand;
import commands.Result;
import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 *         This is a Function that extends the NonUpdatableCommand object. It
 *         behaves in slightly more complex ways than other, more standard
 *         commands, but nevertheless allows for polymorphism inside of Parser.
 *
 */
public class Function extends NonUpdatableCommand {
	private List<String> myParams;
	private List<String> myCommands;
	private Parser myParser;
	private String myName;

	public Function(Visualizer vis, VariableReplacer var_replacer, Parser parser, String name, List<String> params,
			List<String> commands) {
		super(vis, var_replacer, params.size());
		myParser = parser;
		myParams = params;
		myCommands = commands;
		myName = name;
	}

	/**
	 * Here execute is overwritten because I don't want the values of variables
	 * overwritten until execution time on the specific command that contains the
	 * variable itself.
	 */
	@Override
	public String execute() {
		if (!isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(getArgs());

		visCommand(new Result(ans));
		return Double.toString(ans);
	}

	private String replaceParams(List<String> args) {
		List<String> replaced = new ArrayList<>(myCommands);
		for (int i = 0; i < myParams.size(); i++) {
			String param = myParams.get(i);
			for (int j = 0; j < replaced.size(); j++) {
				String command = replaced.get(j);
				if (command.equals(param)) {
					replaced.set(j, args.get(i));
				}
			}
		}
		args.clear();
		return String.join(" ", replaced);
	}

	@Override
	protected double calcValue(List<String> args) {
		return myParser.parse(replaceParams(args));
	}

	/**
	 * The front end uses this method to display the function to the user on screen.
	 */
	@Override
	public String toString() {
		return String.format("to %s [ %s ] [ %s ]", myName, String.join(" ", myParams), String.join(" ", myCommands));
	}
}
