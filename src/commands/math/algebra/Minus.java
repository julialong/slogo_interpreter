package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.VariableReplacer;
import view.Visualizer;

public class Minus extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;
	
	public Minus(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return -double_args.get(0);
	}

}
