package commands.math.misc;

import java.util.List;

import commands.NonTurtleCommand;

public class PiCommand extends NonTurtleCommand {

	public PiCommand() {
		super(0);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.PI;
	}


}
