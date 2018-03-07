package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Log extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 1;
	
	public Log(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.log(args.get(0));
	}
}
