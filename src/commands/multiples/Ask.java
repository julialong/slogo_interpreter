package commands.multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import parser.Parser;
import view.Visualizer;

public class Ask extends Multiple {
	
	private static final int NUM_ARGS = 2;
	
	private Parser myParser;
	private Set<String> myActives;

	public Ask(Visualizer vis, Parser parser, Set<String> actives) {
		super(vis, NUM_ARGS);
		myParser = parser;
		myActives = actives;
	}

	private void activate(List<String> replace) {
		myActives.clear();
		for (int i=0; i < replace.size(); i++) {
			myActives.add(replace.get(i));
		}
	}

	@Override
	protected double calcValue(List<String> args) {
		List<String> input = argsToList(args);
		List<String> old_actives = new ArrayList<>(myActives);
		int[] turtles = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1);
		activate(input.subList(turtles[0] + 1, turtles[1]));
		double ans = myParser.parse(String.join(" ", input.subList(commands[0] + 1, commands[1])));
		activate(old_actives);
		modifyList(input, commands[1]);
		return ans;
	}

}
