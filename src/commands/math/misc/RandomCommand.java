package commands.math.misc;

import java.util.List;

import commands.NonTurtleCommand;

public class RandomCommand extends NonTurtleCommand {

	public RandomCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.random() * getArgs().get(0);
	}

}
