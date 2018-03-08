package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class Tan extends Trig {

	public Tan(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.tan(args.get(0));
	}

}
