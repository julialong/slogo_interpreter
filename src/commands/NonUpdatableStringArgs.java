package commands;

import java.util.List;

import view.Visualizer;

public abstract class NonUpdatableStringArgs extends Command {

	public NonUpdatableStringArgs(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(getStringArgs());
		visCommand(new Result(ans));
		return Double.toString(ans);
	}
	
	protected abstract double calcValue(List<String> args);
}
