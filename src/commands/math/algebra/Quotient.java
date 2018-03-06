package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableCommand;
import view.Visualizer;

public class Quotient extends NonUpdatableCommand {

	public Quotient(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return args.get(0) / args.get(1);
	}

}