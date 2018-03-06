package commands.booleans;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public abstract class Boolean extends NonUpdatableDoubleArgs {
	
	public Boolean(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	protected Double boolToDouble(boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}

}
