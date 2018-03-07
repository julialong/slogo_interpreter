package commands.multiples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class Turtles extends Multiple {
	
	private static final int NUM_ARGS = 2;

	public Turtles(Visualizer vis, Parser parser, Set<String> actives, Map<String, Updatable> updatables) {
		super(vis, parser, actives, updatables, NUM_ARGS);
	}
	
	@Override
	protected double calcValue(List<String> args) {
		return getUpdatables().size();
	}

}
