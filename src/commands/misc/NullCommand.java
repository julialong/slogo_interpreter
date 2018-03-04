package commands.misc;

import commands.Command;
import view.Visualizer;

public class NullCommand extends Command {
	
	public NullCommand(Visualizer vis) {
		super(vis, Integer.MAX_VALUE);
	}

	@Override
	public Double execute() {
		return Double.MAX_VALUE;
	}
	
	@Override 
	public boolean isReady() {
		return false;
	}
}
