package commands;

import java.util.List;

import view.Visualizer;

public abstract class NonUpdatableDoubleArgs extends Command {
	
	public NonUpdatableDoubleArgs(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(getDoubleArgs());
		visCommand(new Result(ans));
		return Double.toString(ans);
	}
	
	protected abstract double calcValue(List<Double> args);
}