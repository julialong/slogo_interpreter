package commands.misc;

import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Number extends NonUpdatableDoubleArgs {
	
	private static final int NUM_ARGS = 1;

	public Number(Visualizer vis) {
		super(vis, NUM_ARGS);
	}
	
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		double ans = calcValue(getDoubleArgs());
		return Double.toString(ans);
	}
	
	@Override
	protected double calcValue(List<Double> args) {
		return args.get(0);
	}

}
