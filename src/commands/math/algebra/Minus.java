package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Minus extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 1;
	
	public Minus(Visualizer vis) {
		super(vis,  NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return -args.get(0);
	}

}
