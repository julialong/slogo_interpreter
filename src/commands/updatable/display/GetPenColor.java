package commands.updatable.display;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class GetPenColor extends UpdatableCommand {
	
	private static final int NUM_ARGS = 0;

	public GetPenColor(Visualizer vis, int num_args, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getPenColor();
	}

}
