package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class Left extends UpdatableCommand {

	public Left(Visualizer vis, Updatable updatable) {
		super(vis, 1, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.rotate(args.get(0));
	}

}