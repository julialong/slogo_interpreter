package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class LogCommand extends NonUpdatableCommand {
	
	public LogCommand(Visualizer vis) {
		super(vis, 1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.log(getArgs().get(0));
	}
}
