package commands.misc;

import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class SetPalette extends NonUpdatableDoubleArgs {

	private static final int NUM_ARGS = 4;

	private Visualizer myVis;

	public SetPalette(Visualizer vis) {
		super(vis, NUM_ARGS);
		myVis = vis;
	}

	@Override
	protected double calcValue(List<Double> args) {
		System.out.println("setting: " + args);
		return myVis.setPalette(args.get(0).intValue(),
				args.get(1),
				args.get(2),
				args.get(3));
	}

}
