package commands.updatable.queries;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class GetPenColorCommand extends UpdatableCommand {

	public GetPenColorCommand(Visualizer vis, int num_args, Updatable updatable) {
		super(vis, 0, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getPenColor();
	}

}
