package commands.math.misc;

import java.util.List;

import commands.Command;

public class PiCommand extends Command {

	public PiCommand() {
		super(0);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.PI;
	}


}
