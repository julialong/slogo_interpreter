package commands;

import java.util.List;

import commands.factory.VariableReplacer;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 *         This class is for commands that don't touch the Updatable: i.e., they
 *         don't act on the turtle at all. It inherits from Command because it
 *         is a more specific kind of Command.
 *
 */
public abstract class NonUpdatableCommand extends Command {

	public NonUpdatableCommand(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		super(vis, var_replacer, num_args);
	}

	@Override
	protected double performCalculation(List<String> args) {
		return calcValue(args);
	}

	protected abstract double calcValue(List<String> args);
}
