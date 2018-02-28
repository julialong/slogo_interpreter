package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class HomeCommand extends UpdatableCommand {

	public HomeCommand(Visualizer vis, Updatable updatable) {
		super(vis, 0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.home();
	}

}
