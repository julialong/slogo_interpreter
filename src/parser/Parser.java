package parser;

import java.util.LinkedList;
import java.util.List;

import commands.Command;
import commands.factory.CommandFactory;
import slogo_team07.Updatable;
import view.Visualizer;

public class Parser implements VariableTruthometer {

	private CommandFactory myCommandFactory;
	private Sanitizer mySanitizer;

	public Parser(Visualizer vis) {
		myCommandFactory = new CommandFactory(vis, this);
		mySanitizer = new Sanitizer();
	}

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

	private String traverse(List<String> input, Updatable current) {
		if (input.isEmpty()) {
			return null;
		}

		String next = input.remove(0).toLowerCase();
		if (isVariable(next)) {
			return next;
		} else if (isID(next)) {
			if (current == null) {
				current = myCommandFactory.getCurrent();
			}
			return Double.toString(current.getId());
		} else if (isArgument(next)) {
			return next;
		}

		String ans = null;
		List<String> temp = input;
		for (Command node : myCommandFactory.createCommands(next)) {
			temp = new LinkedList<>(input);
			while (!node.isReady()) {
				if (node.hasUpdatable()) {
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

	private Boolean isArgument(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*") 
				|| string.matches("^\\[.*]$")
				|| !isRegistered(string);
	}

	private boolean isRegistered(String var) {
		return myCommandFactory.isRegistered(var);
	}

	private boolean isID(String next) {
		return next.equals(CommandFactory.ID);
	}

	public void updateLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
