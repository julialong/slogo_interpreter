package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

public class HideTurtle extends UpdatableCommand {

	private static final int NUM_ARGS = 0;

	public HideTurtle(Visualizer vis, VariableReplacer var_replacer, Updatable updatable) {
		super(vis, var_replacer, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValue(Updatable updatable, List<Double> args) {
		return updatable.setVisible(false);
	}

}
