package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import commands.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

public class Forward extends UpdatableCommand {
	
	private static final int NUM_ARGS = 1;
	
	public Forward(Visualizer vis, VariableReplacer var_replacer, Updatable updatable) {
		super(vis, var_replacer, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		System.out.println(args);
		return updatable.move(args.get(0));
	}
	
}
