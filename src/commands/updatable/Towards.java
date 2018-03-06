package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class Towards extends UpdatableCommand {

	public Towards(Visualizer vis, Updatable updatable) {
		super(vis, 2, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setFacing(args.get(0), args.get(1));
	}
}
