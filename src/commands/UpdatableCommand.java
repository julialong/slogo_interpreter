package commands;

import java.util.List;

import slogo_team07.Updatable;

public abstract class UpdatableCommand extends Command {
	
	private Updatable myUpdatable;
	private Double ans;
	
	public UpdatableCommand(int num_args, Updatable updatable) {
		super(num_args);
		myUpdatable = updatable;
	}
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		ans = calcValues(myUpdatable, getArgs());
		return new Result(ans);
	}

	@Override
	public double getAns() {
		return ans;
	}

	protected abstract Double calcValues(Updatable updatable, List<Double> args);
}
