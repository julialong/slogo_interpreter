package commands.updatable.display;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetPenSize extends UpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public SetPenSize(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPenWidth(args.get(0));
	}

}
