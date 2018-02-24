package commands.math.misc;

import java.util.List;

import commands.NonTurtleCommand;

public class LogCommand extends NonTurtleCommand {
	
	public LogCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.log(getArgs().get(0));
	}
}
