package commands.updatable.getters;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class GetHeading extends UpdatableCommand {

	public GetHeading(Updatable updatable) {
		super(0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getHeading();
	}

}
