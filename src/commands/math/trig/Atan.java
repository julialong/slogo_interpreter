package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class Atan extends Trig {

	public Atan(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.atan(double_args.get(0));
	}

}
