package parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import commands.Command;
import commands.CommandFactory;
import slogo_team07.Updatable;
import view.Visualizer;

public class Parser {

	private CommandFactory myCommandFactory;
	private Map<String, String> myVarMap = new HashMap<>();
	private Map<String, Function> myFuncMap = new HashMap<>();
	private Sanitizer mySanitizer;

	public Parser(Visualizer vis) {
		myCommandFactory = new CommandFactory(vis, this);
		mySanitizer = new Sanitizer(myVarMap, myFuncMap);
	}

	public double parse(String s) {
		List<String> input = mySanitizer.sanitize(s);

		double ans = 0.0;
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
		if (isArgument(next)) {
			return next;
		}

		List<Updatable> actives = myCommandFactory.getActiveUpdatables();
		String ans = null;
		List<String> temp = input;
		List<Command> commandables = myCommandFactory.createCommands(next, actives);
		System.out.println(commandables);
		System.out.println(commandables.size());
		for (Command node : commandables) {
			temp = new LinkedList<>(input);
			while (!node.isReady()) {
				node.inject(traverse(temp));
			}
			System.out.println("executing2");
			ans = node.execute();
		}
		input.clear();
		for (String s : temp) {
			input.add(s);
		}
		return ans;
	}

	private Boolean isArgument(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*") || string.matches("^\\[.*]$");
	}
}
