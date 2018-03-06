package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class Random extends NonUpdatableCommand {

	public Random(Visualizer vis) {
		super(vis, 1);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.random() * getArgs().get(0);
	}

}
