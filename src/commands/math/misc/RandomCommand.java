package commands.math.misc;

import java.util.List;

import commands.Command;

public class RandomCommand extends Command {

	public RandomCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.random() * getArgs().get(0);
	}

}
