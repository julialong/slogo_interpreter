package commands.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class SetBackground extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;
	
	private Visualizer myVis;

	public SetBackground(Visualizer vis) {
		super(vis, NUM_ARGS);
		myVis = vis;
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		Double dex = double_args.get(0);
		myVis.setBackground(dex.intValue());
		return dex;
	}

}
