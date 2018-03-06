package commands.math.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Pi extends NonUpdatableDoubleArgs {

	public Pi(Visualizer vis) {
		super(vis, 0);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.PI;
	}


}
