package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Log extends NonUpdatableDoubleArgs {
	
	public Log(Visualizer vis) {
		super(vis, 1);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.log(args.get(0));
	}
}
