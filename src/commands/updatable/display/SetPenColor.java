package commands.updatable.display;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetPenColor extends UpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public SetPenColor(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPenColor(args.get(0).intValue());
	}

}
