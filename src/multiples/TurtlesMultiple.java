package multiples;

import java.util.List;
import java.util.Map;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class TurtlesMultiple extends Multiple {
	
	public TurtlesMultiple(Visualizer vis, Parser parser, List<String> actives) {
		super(vis, parser, actives, 0);
	}

	@Override
	public double manage(List<String> input) {
		return myUpdatables.size();
	}

}
