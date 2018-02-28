package commands.booleans;

import java.util.List;

import view.Visualizer;

public class AndCommand extends BooleanCommand {

	public AndCommand(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = (args.get(0) != 0.0) && (args.get(1) != 0.0);
		return boolToDouble(b);
	}
}
