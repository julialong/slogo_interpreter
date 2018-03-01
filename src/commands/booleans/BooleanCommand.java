package commands.booleans;

import commands.NonUpdatableCommand;
import view.Visualizer;

public abstract class BooleanCommand extends NonUpdatableCommand {
	
	public BooleanCommand(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	protected Double boolToDouble(Boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}
}
