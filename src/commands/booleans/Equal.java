package commands.booleans;

import java.util.List;

import view.Visualizer;

public class Equal extends Boolean {
	
	private static final int NUM_ARGS = 2;
	
	public Equal(Visualizer vis) {
		super(vis, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		boolean b = double_args.get(0) == double_args.get(1);
		return boolToDouble(b);
	}
}
