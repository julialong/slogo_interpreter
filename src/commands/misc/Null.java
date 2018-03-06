package commands.misc;

import commands.Command;
import view.Visualizer;

public class Null extends Command {
	
	public Null(Visualizer vis) {
		super(vis, Integer.MAX_VALUE);
	}

	@Override
	public double execute() {
		return Double.MAX_VALUE;
	}
	
	@Override 
	public boolean isReady() {
		return false;
	}
}
