package commands.updatable.display;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetShape extends UpdatableCommand {
	
	private static final int NUM_ARGS = 1;
	
	public SetShape(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setShape(args.get(0).intValue());
	}

}
