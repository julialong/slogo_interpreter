package commands.multiples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.Parser;
import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

public class Tell extends Multiple {
	

	public Tell(Visualizer vis, Parser parser, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, parser, actives, updatables, 2);
	}

	@Override
	public double manage(List<String> input) {
		int[] brackets = findBrackets(input, 0);
		getActives().clear();
		String num = "0";
		for (int i=1; i < brackets[1]; i++) {
			num = input.get(i);
			if (!getUpdatables().containsKey(num)) {
				addTurtle(num);
			}
			getActives().add(num);
		}
		
		modifyList(input, brackets[1]);
		
		double ans = Double.parseDouble(num);
		return ans;
	}

	private void addTurtle(String id) {
		Turtle turtle = new Turtle(id);
		getUpdatables().put(id, turtle);
		getVis().addDrawable(turtle);
	}
}
