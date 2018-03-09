package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class Sin extends Trig {

	public Sin(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.sin(double_args.get(0));
	}

}
