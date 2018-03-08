package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Quotient extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 2;

	public Quotient(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return args.get(0) / args.get(1);
	}

}
