package commands.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

public class SetBackground extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;
	
	private Visualizer myVis;

	public SetBackground(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
		myVis = vis;
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		Double dex = double_args.get(0);
		myVis.setBackground(dex.intValue());
		return dex;
	}

}
