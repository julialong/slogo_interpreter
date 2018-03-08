package commands.multiples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

public class Tell extends Multiple {
	
	private static final int NUM_ARGS = 1;
	
	private Visualizer myVis;
	private Set<String> myActives;
	private Map<String, Updatable> myUpdatables;

	public Tell(Visualizer vis, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, NUM_ARGS);
		myVis = vis;
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
		String num = null;
		for (int i=1; i < brackets[1]; i++) {
			num = input.get(i);
			if (!myUpdatables.containsKey(num)) {
				addTurtle(num);
			}
			myActives.add(num);
		}
		
		modifyList(input, brackets[1]);
		
		return Double.parseDouble(num);
	}
}
