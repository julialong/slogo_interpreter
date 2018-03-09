package commands.booleans;

import java.util.List;

import commands.VariableReplacer;
import view.Visualizer;

public class Less extends Boolean {
	
	private static final int NUM_ARGS = 2;

	public Less(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		boolean b = double_args.get(0) < double_args.get(1);
		return boolToDouble(b);
	}
}
