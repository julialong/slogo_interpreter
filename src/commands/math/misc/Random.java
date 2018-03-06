package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Random extends NonUpdatableDoubleArgs {

	public Random(Visualizer vis) {
		super(vis, 1);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.random() * args.get(0);
	}

}
