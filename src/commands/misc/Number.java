package commands.misc;

import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Number extends NonUpdatableDoubleArgs {

	public Number(Visualizer vis) {
		super(vis, 1);
	}
	
	@Override
	public double execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		return calcValue(parseToDouble(getArgs()));
	}
	
	@Override
	protected double calcValue(List<Double> args) {
		return args.get(0);
	}

}
