package commands.updatable.queries;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class GetYCor extends UpdatableCommand {

	public GetYCor(Updatable updatable) {
		super(0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getY();
	}

}
