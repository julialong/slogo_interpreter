package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class Sin extends Trig {

	public Sin(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.sin(args.get(0));
	}

}
