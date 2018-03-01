package commands.booleans;

import java.util.List;

import view.Visualizer;

public class LessCommand extends BooleanCommand {

	public LessCommand(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = args.get(0) < args.get(1);
		return boolToDouble(b);
	}
}
