package commands.multiples;

import java.util.List;
import java.util.Map;

import slogo_team07.Updatable;
import view.Visualizer;

public class Turtles extends Multiple {
	
	private static final int NUM_ARGS = 0;
	
	private Map<String, Updatable> myUpdatables;

	public Turtles(Visualizer vis, Map<String, Updatable> updatables) {
		super(vis, NUM_ARGS);
		myUpdatables = updatables;
	}
	
	@Override
	protected double calcValue(List<String> args) {
		return myUpdatables.size();
	}

}
