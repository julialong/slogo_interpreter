package commands.math.trig;

import commands.NonUpdatableCommand;
import view.Visualizer;

public abstract class Trig extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public Trig(Visualizer vis) {
		super(vis, NUM_ARGS);
	}
}
