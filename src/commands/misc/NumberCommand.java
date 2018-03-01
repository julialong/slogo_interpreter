package commands.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class NumberCommand extends NonUpdatableCommand {

	public NumberCommand(Visualizer vis) {
		super(vis, 1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return args.get(0);
	}

}
