package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.VariableReplacer;
import view.Visualizer;

public class Log extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;
	
	public Log(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.log(double_args.get(0));
	}
}
