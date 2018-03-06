package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Pow extends NonUpdatableDoubleArgs {

	public Pow(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.pow(args.get(0), args.get(1));
	}

}
