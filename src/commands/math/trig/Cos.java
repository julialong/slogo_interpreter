package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class Cos extends Trig {

	public Cos(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.cos(args.get(0));
	}

}
