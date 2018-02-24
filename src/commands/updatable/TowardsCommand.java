package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class TowardsCommand extends UpdatableCommand {

	public TowardsCommand(Updatable updatable) {
		super(2, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setFacing(args.get(0), args.get(1));
	}
}
