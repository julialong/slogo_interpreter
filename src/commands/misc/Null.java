package commands.misc;

import commands.Command;
import view.Visualizer;

public class Null extends Command {
	
	private static final int NUM_ARGS = Integer.MAX_VALUE;
	
	public Null(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	public String execute() {
		return null;
	}
	
	@Override 
	public boolean isReady() {
		return false;
	}
}
