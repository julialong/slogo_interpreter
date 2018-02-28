package commands.updatable.queries;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class GetYCor extends UpdatableCommand {

	public GetYCor(Visualizer vis, Updatable updatable) {
		super(vis, 0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getY();
	}

}
