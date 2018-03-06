package commands.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class SetBackground extends NonUpdatableDoubleArgs {
	
	private Visualizer myVis;

	public SetBackground(Visualizer vis, int num_args) {
		super(vis, 1);
		myVis = vis;
	}

	@Override
	protected double calcValue(List<Double> args) {
		Double dex = args.get(0);
		myVis.setBackground(dex.intValue());
		return dex;
	}

}
