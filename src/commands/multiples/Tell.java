package commands.multiples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import commands.VariableReplacer;
import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

public class Tell extends Multiple {
	
	private static final int NUM_ARGS = 1;
	
	private Visualizer myVis;
	private Set<String> myActives;
	private Map<String, Updatable> myUpdatables;
	private VariableReplacer myVariableReplacer;

	public Tell(Visualizer vis, VariableReplacer var_replacer, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, var_replacer, NUM_ARGS);
		myVis = vis;
		myActives = actives;
		myUpdatables = updatables;
		myVariableReplacer = var_replacer;
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
			if (isVariable(id)) {
				id = myVariableReplacer.replace(id);
			}
			num = Double.parseDouble(id);
			String num_string = Integer.toString((int) num);
			if (!myUpdatables.containsKey(num_string)) {
				addTurtle(num_string);
			}
			myActives.add(num_string);
		}
		
		modifyList(input, brackets[1]);
		
		return num;
	}
	
	private boolean isVariable(String string) {
		return string.matches(":[a-zA-Z]+");
	}
}
