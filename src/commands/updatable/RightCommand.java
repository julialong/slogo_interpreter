package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class RightCommand extends UpdatableCommand {

	public RightCommand(Visualizer vis, Updatable updatable) {
		super(vis, updatable, 1);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.rotate(-args.get(0));
	}
}
