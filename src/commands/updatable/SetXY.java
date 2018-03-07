package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetXY extends UpdatableCommand {
	
	private static final int NUM_ARGS = 2;

	public SetXY(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPosition(args.get(0), args.get(1));
	}
}
