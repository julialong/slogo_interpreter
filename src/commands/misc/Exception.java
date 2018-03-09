package commands.misc;

import commands.Command;
import commands.VariableReplacer;
import view.Visualizer;

public class Exception extends Command {
	
	private static final int NUM_ARGS = 0;
	
	public Exception(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	public String execute() {
		// do nothing for now, maybe pass an error message later
		return null;
	}
}
