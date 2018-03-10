package commands.booleans;

import java.util.List;

import commands.VariableReplacer;
import view.Visualizer;

public class Equal extends Boolean {
	
	private static final int NUM_ARGS = 2;
	
	public Equal(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		boolean are_equal = double_args.get(0).equals(double_args.get(1));
		return boolToDouble(are_equal);
	}
}
