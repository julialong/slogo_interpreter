package commands.math.trig;

import java.util.List;

import commands.factory.VariableReplacer;
import view.Visualizer;

public class Tan extends Trig {

	public Tan(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.tan(double_args.get(0));
	}

}
