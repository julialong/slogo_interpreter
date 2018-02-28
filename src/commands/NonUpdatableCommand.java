package commands;

import java.util.List;

import view.Visualizer;

public abstract class NonUpdatableCommand extends Command {
	
	private Double ans;

	public NonUpdatableCommand(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	@Override
	public void execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		ans = calcValue(getArgs());
		visCommand(new Result(ans));
	}

	@Override
	public Double getAns() {
		return ans;
	}
	
	protected abstract Double calcValue(List<Double> args);

}
