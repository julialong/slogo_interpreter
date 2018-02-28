package commands.math.algebra;

import java.util.List;

import view.Visualizer;

public class DifferenceCommand extends AlgebraCommand {

	public DifferenceCommand(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return args.get(0) - args.get(1);
	}

}
