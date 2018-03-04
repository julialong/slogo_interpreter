package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetXYCommand extends UpdatableCommand {

	public SetXYCommand(Visualizer vis, Updatable updatable) {
		super(vis, updatable, 2);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPosition(args.get(0), args.get(1));
	}
}
