package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;

public class PiCommand extends NonUpdatableCommand {

	public PiCommand() {
		super(0);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.PI;
	}


}
