package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class Cos extends Trig {

	public Cos(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.cos(double_args.get(0));
	}

}
