package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetHeadingCommand extends UpdatableCommand {

	public SetHeadingCommand(Visualizer vis, Updatable updatable) {
		super(vis, 1, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setHeading(args.get(0));
	}

}
