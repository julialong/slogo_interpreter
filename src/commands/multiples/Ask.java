package commands.multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import commands.factory.VariableReplacer;
import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class Ask extends TurtleCreator {
	
	private static final int NUM_ARGS = 2;
	
	private Parser myParser;
	private Set<String> myActives;

	public Ask(Visualizer vis, VariableReplacer var_replacer, Parser parser, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, var_replacer, NUM_ARGS, actives, updatables);
		myParser = parser;
		myActives = actives;
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
