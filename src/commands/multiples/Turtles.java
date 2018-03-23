package commands.multiples;

import java.util.List;
import java.util.Map;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

public class Turtles extends Multiple {

	private static final int NUM_ARGS = 0;

	private Map<String, Updatable> myUpdatables;

	public Turtles(Visualizer vis, VariableReplacer var_replacer, Map<String, Updatable> updatables) {
		super(vis, var_replacer, NUM_ARGS);
		myUpdatables = updatables;
	}

	@Override
	protected double calcValue(List<String> args) {
		return myUpdatables.size();
	}

}
