package commands;

import java.util.List;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 *         This class is for commands that don't touch the Updatable: i.e., they don't act on the
 *         turtle at all. It implements the formatArgs() method and delegates that responsibility to
 *         calcValue() in contrast to UpdatableCommand's calcValue(), which takes different
 *         arguments. This class is a slight specialization of Command that allows its subclasses to
 *         not have to worry about implementing getUpdatable() or having their arguments formatted
 *         properly.
 *
 */
public abstract class NonUpdatableCommand extends Command {

	public NonUpdatableCommand(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		super(vis, var_replacer, num_args);
	}

	@Override
	protected double formatArgs(List<String> args) {
		return calcValue(args);
	}

	@Override
	public Updatable getUpdatable() {
		return null;
	}

	protected abstract double calcValue(List<String> args);
	
}