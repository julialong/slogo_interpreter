package commands.math.misc;

import java.util.List;

import commands.NonTurtleCommand;

public class PowCommand extends NonTurtleCommand {

	public PowCommand() {
		super(2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.pow(getArgs().get(0), getArgs().get(1));
	}

}
