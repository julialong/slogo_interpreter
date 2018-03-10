package commands.math.trig;

import java.util.List;

import commands.factory.VariableReplacer;
import view.Visualizer;

public class Sin extends Trig {

	public Sin(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return Math.sin(double_args.get(0));
	}

}
