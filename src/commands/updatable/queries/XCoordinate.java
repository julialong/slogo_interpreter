package commands.updatable.queries;

import java.util.List;

import commands.UpdatableCommand;
import commands.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

public class XCoordinate extends UpdatableCommand {
	
	private static final int NUM_ARGS = 0;

	public XCoordinate(Visualizer vis, VariableReplacer var_replacer, Updatable updatable) {
		super(vis, var_replacer, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.getX();
	}

}
