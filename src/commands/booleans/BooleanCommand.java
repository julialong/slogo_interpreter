package commands.booleans;

import commands.Command;

public abstract class BooleanCommand extends Command {
	
	public BooleanCommand(int num_args) {
		super(num_args);
	}
	
	protected Double boolToDouble(Boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}
}
