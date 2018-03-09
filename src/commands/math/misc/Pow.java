package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class Pow extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 2;

	public Pow(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.pow(double_args.get(0), double_args.get(1));
	}

}
