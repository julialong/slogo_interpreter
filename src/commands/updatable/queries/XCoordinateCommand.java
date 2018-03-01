package commands.updatable.queries;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class XCoordinateCommand extends UpdatableCommand {

	public XCoordinateCommand(Visualizer vis, Updatable updatable) {
		super(vis, updatable, 0);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getX();
	}

}
