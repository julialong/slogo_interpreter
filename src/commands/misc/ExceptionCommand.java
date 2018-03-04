package commands.misc;

import commands.Command;
import view.Visualizer;

public class ExceptionCommand extends Command {
	
	public ExceptionCommand(Visualizer vis) {
		super(vis, 0);
	}

	@Override
	public double execute() {
		// do nothing for now, maybe pass an error message later
		return Double.MAX_VALUE;
	}
}
