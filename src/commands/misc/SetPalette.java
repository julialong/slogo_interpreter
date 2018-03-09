package commands.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.VariableReplacer;
import view.Visualizer;

public class SetPalette extends NonUpdatableCommand {

	private static final int NUM_ARGS = 4;

	private Visualizer myVis;

	public SetPalette(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
		myVis = vis;
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return myVis.setPalette(double_args.get(0).intValue(),
				double_args.get(1),
				double_args.get(2),
				double_args.get(3));
	}

}
