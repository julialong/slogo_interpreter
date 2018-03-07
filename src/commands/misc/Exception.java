package commands.misc;

import commands.Command;
import view.Visualizer;

public class Exception extends Command {
	
	private static final int NUM_ARGS = 0;
	
	public Exception(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	public String execute() {
		// do nothing for now, maybe pass an error message later
		return null;
	}
}
