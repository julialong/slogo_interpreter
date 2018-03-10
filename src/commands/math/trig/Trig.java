package commands.math.trig;

import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

public abstract class Trig extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public Trig(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}
}
