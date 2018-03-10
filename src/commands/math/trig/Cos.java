package commands.math.trig;

import java.util.List;

import commands.VariableReplacer;
import view.Visualizer;

public class Cos extends Trig {

	public Cos(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.cos(double_args.get(0));
	}

}
