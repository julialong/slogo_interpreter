package commands;

import java.util.List;

import view.Visualizer;

public abstract class NonUpdatableCommand extends Command {

	public NonUpdatableCommand(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	public double execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(getArgs());
		visCommand(new Result(ans));
		return ans;
	}
	
	protected abstract double calcValue(List<Double> args);

}
