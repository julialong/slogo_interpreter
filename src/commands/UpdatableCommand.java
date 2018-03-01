package commands;

import java.util.List;

import slogo_team07.Updatable;
import view.Visualizer;

public abstract class UpdatableCommand extends Command {
	
	private Updatable myUpdatable;
	private Double ans;
	
	public UpdatableCommand(Visualizer vis, Updatable updatable, int num_args) {
		super(vis, num_args);
		myUpdatable = updatable;
	}
	
	@Override
	public void execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		ans = calcValues(myUpdatable, getArgs());
		visCommand(new Result(ans));
	}

	@Override
	public Double getAns() {
		return ans;
	}

	protected abstract Double calcValues(Updatable updatable, List<Double> args);
}
