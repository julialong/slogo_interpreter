package commands.misc;

import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

public class Number extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public Number(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}
	
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		double ans = calcValue(getArgs());
		return Double.toString(ans);
	}
	
	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return double_args.get(0);
	}

}
