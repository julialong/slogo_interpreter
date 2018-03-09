package commands.multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import commands.VariableReplacer;
import parser.Parser;
import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

public class Ask extends Multiple {
	
	private static final int NUM_ARGS = 2;
	
	private Visualizer myVis;
	private Parser myParser;
	private Map<String, Updatable> myUpdatables;
	private Set<String> myActives;

	public Ask(Visualizer vis, VariableReplacer var_replacer, Parser parser, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, var_replacer, NUM_ARGS);
		myVis = vis;
		myParser = parser;
		myActives = actives;
		myUpdatables = updatables;
	}
	
	private void addTurtle(String id) {
		Turtle turtle = new Turtle(id);
		myUpdatables.put(id, turtle);
		myVis.addDrawable(turtle);
	}

	private void activate(List<String> replace) {
		myActives.clear();
		for (int i=0; i < replace.size(); i++) {
			double id = myParser.parse(replace.get(i));
			String id_string = Integer.toString((int) id);
			System.out.println(id_string);
			if (!myUpdatables.containsKey(id_string)) {
				addTurtle(id_string);
			}
			myActives.add(id_string);
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
