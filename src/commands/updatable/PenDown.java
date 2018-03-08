package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class PenDown extends UpdatableCommand {
	
	private static final int NUM_ARGS = 0;

	public PenDown(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPenDown(true);
	}

}
