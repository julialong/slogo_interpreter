package commands.booleans;

import java.util.List;

import view.Visualizer;

public class And extends Boolean {
	
	private static final int NUM_ARGS = 2;

	public And(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		boolean b = (!args.get(0).equals(0.0) && !args.get(1).equals(0.0));
		return boolToDouble(b);
	}

}
