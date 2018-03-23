package commands.booleans;

import java.util.List;

import commands.factory.VariableReplacer;
import view.Visualizer;

public class Not extends Boolean {

	private static final int NUM_ARGS = 1;

	public Not(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		boolean is_not = double_args.get(0).equals(0.0);
		return boolToDouble(is_not);
	}
}
