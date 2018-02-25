package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class SetHeadingCommand extends UpdatableCommand {

	public SetHeadingCommand(Updatable updatable) {
		super(1, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setHeading(args.get(0));
	}

}
