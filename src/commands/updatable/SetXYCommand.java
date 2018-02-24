package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class SetXYCommand extends UpdatableCommand {

	public SetXYCommand(Updatable updatable) {
		super(2, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPosition(args.get(0), args.get(1));
	}
}
