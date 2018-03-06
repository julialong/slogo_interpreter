package commands.multiples;

import java.util.List;
import java.util.Map;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class Turtles extends Multiple {

	public Turtles(Visualizer vis, Parser parser, List<String> actives, Map<String, Updatable> updatables) {
		super(vis, parser, actives, updatables, 0);
	}

	@Override
	public double manage(List<String> input) {
		return getUpdatables().size();
	}

}
