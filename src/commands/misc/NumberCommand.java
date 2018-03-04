package commands.misc;

import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableCommand;
import view.Visualizer;

public class NumberCommand extends NonUpdatableCommand {

	public NumberCommand(Visualizer vis) {
		super(vis, 1);
	}
	
	@Override
	public double execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		return calcValue(getArgs());
	}
	
	@Override
	protected double calcValue(List<Double> args) {
		return args.get(0);
	}

}
