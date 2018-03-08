package commands;

import java.util.List;

import slogo_team07.Updatable;
import view.Visualizer;

public abstract class UpdatableCommand extends Command {

	private Updatable myUpdatable;

	public UpdatableCommand(Visualizer vis, int num_args,  Updatable updatable) {
		super(vis, num_args);
		myUpdatable = updatable;
	}

	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		Double ans = calcValues(myUpdatable, getDoubleArgs());
		visCommand(new Result(ans));
		return Double.toString(ans);
	}

	protected abstract double calcValues(Updatable updatable, List<Double> args);
}
