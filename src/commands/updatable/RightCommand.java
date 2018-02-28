package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class RightCommand extends UpdatableCommand {

	public RightCommand(Visualizer vis, Updatable updatable) {
		super(vis, 1, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.rotate(args.get(0));
	}
}
