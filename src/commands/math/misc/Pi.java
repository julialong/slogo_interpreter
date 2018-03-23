package commands.math.misc;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

public class Pi extends NonUpdatableCommand {

	private static final int NUM_ARGS = 0;

	public Pi(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		return Math.PI;
	}

}
