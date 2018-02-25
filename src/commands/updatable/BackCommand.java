package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class BackCommand extends UpdatableCommand {
	
	public BackCommand(Updatable updatable) {
		super(1, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.move(args.get(0));
	}
}
