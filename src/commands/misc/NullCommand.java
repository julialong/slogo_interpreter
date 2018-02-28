package commands.misc;

import commands.Command;
import view.Visualizer;

public class NullCommand extends Command {
	
	public NullCommand(Visualizer vis) {
		super(vis, Integer.MAX_VALUE);
	}

	@Override
	public void execute() {
		// do nothing
	}
	
	@Override 
	public boolean isReady() {
		return false;
	}

	@Override
	public Double getAns() {
		return null;
	}
}
