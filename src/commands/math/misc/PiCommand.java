package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class PiCommand extends NonUpdatableCommand {

	public PiCommand(Visualizer vis) {
		super(vis, 0);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.PI;
	}


}
