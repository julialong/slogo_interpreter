package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Random extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 1;

	public Random(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.random() * args.get(0);
	}

}
