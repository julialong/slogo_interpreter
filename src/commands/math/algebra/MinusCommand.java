package commands.math.algebra;

import java.util.List;

import view.Visualizer;

public class MinusCommand extends AlgebraCommand {
	
	public MinusCommand(Visualizer vis) {
		super(vis,  1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return -args.get(0);
	}

}
