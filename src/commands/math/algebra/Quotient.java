package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.VariableReplacer;
import view.Visualizer;

public class Quotient extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 2;

	public Quotient(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return double_args.get(0) / double_args.get(1);
	}

}
