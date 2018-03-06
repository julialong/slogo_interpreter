package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class Log extends NonUpdatableCommand {
	
	public Log(Visualizer vis) {
		super(vis, 1);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.log(getArgs().get(0));
	}
}
