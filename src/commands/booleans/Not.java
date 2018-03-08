package commands.booleans;

import java.util.List;

import view.Visualizer;

public class Not extends Boolean {
	
	private static final int NUM_ARGS = 1;
	
	public Not(Visualizer vis) {
		super(vis, NUM_ARGS);
	}
	
	@Override
	protected double calcValue(List<Double> args) {
		boolean b = args.get(0) == 0.0; 
		return boolToDouble(b);
	}
}
