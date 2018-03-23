package commands.multiples;

import java.util.List;
import java.util.Map;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

public class Tell extends TurtleCreator {

	private static final int NUM_ARGS = 1;

	public Tell(Visualizer vis, VariableReplacer var_replacer, List<String> actives,
			Map<String, Updatable> updatables) {
		super(vis, var_replacer, NUM_ARGS, actives, updatables);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<String> input = argsToList(args);
		int[] brackets = findBrackets(input, 0);
		double ans = activate(input.subList(1, brackets[1]));
		modifyList(input, brackets[1]);
		return ans;
	}
}
