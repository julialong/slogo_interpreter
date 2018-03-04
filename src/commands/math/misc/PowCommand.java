package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class PowCommand extends NonUpdatableCommand {

	public PowCommand(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.pow(getArgs().get(0), getArgs().get(1));
	}

}
