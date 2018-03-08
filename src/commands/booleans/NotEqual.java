package commands.booleans;

import java.util.List;

import view.Visualizer;

public class NotEqual extends Boolean {
	
	private static final int NUM_ARGS = 2;

	public NotEqual(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<Double> args) {
		boolean b = args.get(0) != args.get(1);
		return boolToDouble(b);
	}
}
