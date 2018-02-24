package commands;

import java.util.List;

public abstract class NonUpdatableCommand extends Command {
	
	private Double ans;

	public NonUpdatableCommand(int num_args) {
		super(num_args);
	}
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		ans = calcValue(getArgs());
		return new Result(ans);
	}
	
	public Double getAns() {
		return ans;
	}
	
	protected abstract Double calcValue(List<Double> args);

}
