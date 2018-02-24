package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class Penup extends UpdatableCommand {

	public Penup(Updatable updatable) {
		super(0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setPen(false);
	}

}
