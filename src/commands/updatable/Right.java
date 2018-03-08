package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class Right extends UpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public Right(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.rotate(-args.get(0));
	}
}
