package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Pow extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 2;

	public Pow(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.pow(args.get(0), args.get(1));
	}

}
