package multiples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

public class TellMultiple extends Multiple {
	
	private Map<String, Updatable> myUpdatables;
	private Visualizer myVis;
	private Set<String> myActives;
	
	public TellMultiple(Map<String, Updatable> updatable_map, Visualizer vis, Set<String> actives) {
		myUpdatables = updatable_map;
		myVis = vis;
		myActives = actives;
	}
	
	@Override
	public double manage(List<String> input) {
		int[] brackets = findBrackets(input, 0);
		myActives.clear();
		for (int i=1; i < brackets[1]; i++) {
			String num = input.get(i);
			if (!myUpdatables.containsKey(num)) {
				addTurtle(num);
			}
			myActives.add(num);
		}
		double ans = Double.parseDouble(input.get(brackets[1] - 1));
		modifyList(input, brackets[1]);
		return ans;
	}
	
	private void addTurtle(String id) {
		Turtle turtle = new Turtle();
		myUpdatables.put(id, turtle);
		myVis.addDrawable(turtle);
	}
}
