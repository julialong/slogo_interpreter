package commands.booleans;

import java.util.List;

import view.Visualizer;

public class NotEqualCommand extends BooleanCommand {

	public NotEqualCommand(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected double calcValue(List<Double> args) {
		Boolean b = args.get(0) != args.get(1);
		return boolToDouble(b);
	}
}
