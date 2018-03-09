package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class Random extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public Random(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.random() * double_args.get(0);
	}

}
