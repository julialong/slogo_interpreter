package commands.multiples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.Parser;
import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

public class Tell extends Multiple {
	
	private static final int NUM_ARGS = 1;
	
	private Visualizer myVis;
	private Parser myParser;
	private Set<String> myActives;
	private Map<String, Updatable> myUpdatables;

	public Tell(Visualizer vis, Parser parser, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, NUM_ARGS);
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

	@Override
	protected double calcValue(List<String> args) {
		List<String> input = argsToList(args);
		int[] brackets = findBrackets(input, 0);
		myActives.clear();
		double num = 0;
		for (String id : input.subList(1, brackets[1])) {
			num = myParser.parse(id);
			String num_string = Integer.toString((int) num);
			if (!myUpdatables.containsKey(num_string)) {
				addTurtle(num_string);
			}
			myActives.add(num_string);
		}
		
		modifyList(input, brackets[1]);
		
		return num;
	}
}
