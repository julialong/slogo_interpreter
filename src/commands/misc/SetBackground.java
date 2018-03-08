package commands.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class SetBackground extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 1;
	
	private Visualizer myVis;

	public SetBackground(Visualizer vis) {
		super(vis, NUM_ARGS);
		myVis = vis;
	}

	@Override
	protected double calcValue(List<Double> args) {
		Double dex = args.get(0);
		myVis.setBackground(dex.intValue());
		return dex;
	}

}
