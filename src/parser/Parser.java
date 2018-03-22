package parser;

import java.util.LinkedList;
import java.util.List;

import commands.Command;
import commands.UpdatableCommand;
import commands.factory.CommandFactory;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * The Class Parser.
 *
 * @author benhubsch
 * 
 * This is the parser for the input that the user enters to the front end. It uses recursion
 * to virtualize a syntax tree and executes commands in the tree as it builds them, not
 * waiting to first build the tree and then traverse it. It takes advantage of polymorphism,
 * relying on the API exposed by Command objects to do its work.
 */
public class Parser implements VariableTruthometer {

	private CommandFactory myCommandFactory;
	private Sanitizer mySanitizer;

	/**
	 * Instantiates a new Parser object.
	 *
	 * @param vis the vis
	 */
	public Parser(Visualizer vis, CommandFactory command_factory, Sanitizer sanitizer) {
		myCommandFactory = command_factory;
		mySanitizer = sanitizer;

		myCommandFactory.setParser(this);
	}

	/**
	 * This is the entry point to the traversal. It sanitizes the input and then loops
	 * over it while it isn't empty -- every time one of those loops returns, that represents
	 * the traversal of a single tree.
	 * @param s The input string.
	 * @return double The return value of the final command executed, which will end up getting
	 * displayed to the user.
	 */
	public double parse(String s) {
		List<String> input = mySanitizer.sanitize(s);
		double ans = -1;
		while (!input.isEmpty()) {
			ans = Double.parseDouble(traverse(input));
		}
		return ans;
	}

	private String traverse(List<String> input) {
		return traverse(input, null);
	}

	/**
	 * This function is the meat of this class. It traverses the tree and executes commands
	 * as they become executable, recursing over child nodes when appropriate.
	 * @param input This is the user input in sanitized list form.
	 * @param current This is the "current" Updatable, which is used to return the proper
	 * ID when called. 
	 * @return
	 */
	private String traverse(List<String> input, Updatable current) {
		if (input.isEmpty()) {
			return null;
		}

		String next = input.remove(0).toLowerCase();
		if (isID(next)) {
			return myCommandFactory.getId(current);
		} else if (isArgument(next) || isVariable(next)) {
			return next;
		}

		String ans = null;
		List<String> temp = input;
		for (Command node : myCommandFactory.createCommands(next)) {
			temp = new LinkedList<>(input);
			while (!node.isReady()) {
				if (isUpdatable(node)) {
					current = node.getUpdatable();
				}
				node.inject(traverse(temp, current));
			}
			ans = node.execute();
		}
		clearAndAdd(input, temp);
		return ans;
	}

	private void clearAndAdd(List<String> target, List<String> source) {
		target.clear();
		for (String current : source) {
			target.add(current);
		}
	}

	private boolean isArgument(String string) {
		return isNumber(string)
				|| isList(string)
				|| !myCommandFactory.isKnownCommand(string);
	}

	private boolean isNumber(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*");
	}

	private boolean isList(String string) {
		return string.matches("^\\[.*]$");
	}

	private boolean isID(String next) {
		return next.equals(CommandFactory.ID);
	}

	private boolean isUpdatable(Command node) {
		return node.getClass().getSuperclass().equals(UpdatableCommand.class);
	}
}
