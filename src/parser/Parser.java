package parser;

import java.util.LinkedList;
import java.util.List;

import commands.Command;
import commands.factory.CommandFactory;
import view.Visualizer;

public class Parser {

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
		if (input.isEmpty()) {
			return null;
		}

		String next = input.remove(0).toLowerCase();
		if (isVariable(next)) {
			return isRegistered(next) ? myCommandFactory.getVar(next) : next;
		} else if (isArgument(next) || !isRegistered(next)) {
			return next;
		}

		String ans = null;
		List<String> temp = input;
		for (Command node : myCommandFactory.createCommands(next)) {
			temp = new LinkedList<>(input);
			while (!node.isReady()) {
				node.inject(traverse(temp));
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
				|| string.matches("^\\[.*]$");
	}

	private boolean isVariable(String string) {
		return string.matches(":[a-zA-Z]+");
	}

	private boolean isRegistered(String var) {
		return myCommandFactory.isRegistered(var);
	}
}
