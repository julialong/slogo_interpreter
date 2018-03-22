package commands;

import java.util.List;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 * This class is for commands that act on the Updatable: i.e., they act on the turtle. 
 * It inherits from Command because it is a more specific kind of Command.
 *
 */
public abstract class UpdatableCommand extends Command {

	private Updatable myUpdatable;

	public UpdatableCommand(Visualizer vis, VariableReplacer var_replacer, int num_args, Updatable updatable) {
		super(vis, var_replacer, num_args);
		myUpdatable = updatable;
	}
	
	@Override
	protected double performCalculation(List<String> args) {
		return calcValues(myUpdatable, parseToDouble(args));
	}
	
	protected abstract double calcValues(Updatable updatable, List<Double> args);

	@Override
	public Updatable getUpdatable() {
		return myUpdatable;
	}
}
