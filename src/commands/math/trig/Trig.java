package commands.math.trig;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public abstract class Trig extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 1;

	public Trig(Visualizer vis) {
		super(vis, NUM_ARGS);
	}
}
