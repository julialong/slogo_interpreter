package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class PenUpCommand extends UpdatableCommand {

	public PenUpCommand(Visualizer vis, Updatable updatable) {
		super(vis, updatable, 0);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPen(false);
	}

}
