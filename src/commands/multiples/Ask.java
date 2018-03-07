package commands.multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class Ask extends Multiple {
	
	private static final int NUM_ARGS = 2;

	public Ask(Visualizer vis, Parser parser, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, parser, actives, updatables, NUM_ARGS);
	}

	private void activate(List<String> replace) {
		getActives().clear();
		for (int i=0; i < replace.size(); i++) {
			getActives().add(replace.get(i));
		}
	}

	@Override
	protected double calcValue(List<String> args) {
		List<String> input = argsToList(args);
		List<String> old_actives = new ArrayList<>(getActives());
		int[] turtles = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1);
		activate(input.subList(turtles[0] + 1, turtles[1]));
		double ans = getParser().parse(String.join(" ", input.subList(commands[0] + 1, commands[1])));
		activate(old_actives);
		modifyList(input, commands[1]);
		return ans;
	}

}
