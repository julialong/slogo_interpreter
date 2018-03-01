package commands.misc;

import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableCommand;
import view.Visualizer;

public class NumberCommand extends NonUpdatableCommand {
	
	private Double ans;

	public NumberCommand(Visualizer vis) {
		super(vis, 1);
	}
	
	@Override
	public void execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		ans = calcValue(getArgs());
	}

	@Override
	public Double getAns() {
		return ans;
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return args.get(0);
	}

}
