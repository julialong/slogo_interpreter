package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class SetShapeCommand extends UpdatableCommand {
	
	public SetShapeCommand(Visualizer vis, Updatable updatable) {
		super(vis, 1, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setShape(args.get(0).intValue());
	}

}
