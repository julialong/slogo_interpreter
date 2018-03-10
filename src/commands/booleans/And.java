package commands.booleans;

import java.util.List;

import commands.factory.VariableReplacer;
import view.Visualizer;

public class And extends Boolean {
	
	private static final int NUM_ARGS = 2;

	public And(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		boolean and = (!double_args.get(0).equals(0.0) && !double_args.get(1).equals(0.0));
		return boolToDouble(and);
	}

}
