package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetPalette extends UpdatableCommand {

	public SetPalette(Visualizer vis, Updatable updatable) {
		super(vis, 4, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPalette(args.get(0).intValue(),
									args.get(1),
									args.get(2),
									args.get(3));
	}

}
