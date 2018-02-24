package commands.booleans;

import commands.NonUpdatableCommand;

public abstract class BooleanCommand extends NonUpdatableCommand {
	
	public BooleanCommand(int num_args) {
		super(num_args);
	}
	
	protected Double boolToDouble(Boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}
}
