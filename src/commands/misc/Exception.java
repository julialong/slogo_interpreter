package commands.misc;

import commands.Command;
import view.Visualizer;

public class Exception extends Command {
	
	public Exception(Visualizer vis) {
		super(vis, 0);
	}

	@Override
	public String execute() {
		// do nothing for now, maybe pass an error message later
		return null;
	}
}
