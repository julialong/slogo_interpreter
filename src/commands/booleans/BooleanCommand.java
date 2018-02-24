package commands.booleans;

import commands.NonTurtleCommand;

public abstract class BooleanCommand extends NonTurtleCommand {
	
	public BooleanCommand(int num_args) {
		super(num_args);
	}
	
	protected Double boolToDouble(Boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}
}
