package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Pi extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 0;

	public Pi(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.PI;
	}


}
