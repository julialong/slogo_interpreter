package commands.booleans;

import commands.NonUpdatableCommand;
import commands.VariableReplacer;
import view.Visualizer;

public abstract class Boolean extends NonUpdatableCommand {
	
	public Boolean(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		super(vis, var_replacer, num_args);
	}
	
	protected Double boolToDouble(boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}

}
