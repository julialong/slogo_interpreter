package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class Minus extends NonUpdatableCommand {
	
	public Minus(Visualizer vis) {
		super(vis,  1);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return -args.get(0);
	}

}