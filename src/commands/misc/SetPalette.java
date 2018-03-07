package commands.misc;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetPalette extends UpdatableCommand {
	
	private static final int NUM_ARGS = 4;
	
	private Visualizer myVis;

	public SetPalette(Visualizer vis, Updatable updatable) {
		super(vis, NUM_ARGS, updatable);
		myVis = vis;
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return myVis.setPalette(args.get(0).intValue(),
									args.get(1),
									args.get(2),
									args.get(3));
	}

}
