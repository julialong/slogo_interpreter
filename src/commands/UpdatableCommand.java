package commands;

import java.util.List;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 *         This class is for commands that act on the Updatable: i.e., they act on the turtle. It
 *         implements the formatArgs() method and delegates that responsibility to calcValue() in
 *         contrast to NonUpdatableCommand's calcValue(), which takes different arguments. This
 *         class is a slight specialization of Command that allows its subclasses to not have to
 *         worry about implementing getUpdatable() or having their arguments formatted properly.
 *
 */
public abstract class UpdatableCommand extends Command {

	private Updatable myUpdatable;

	public UpdatableCommand(Visualizer vis, VariableReplacer var_replacer, int num_args, Updatable updatable) {
		super(vis, var_replacer, num_args);
		myUpdatable = updatable;
	}

	@Override
	protected double formatArgs(List<String> args) {
		return calcValue(myUpdatable, parseToDouble(args));
	}

	@Override
	public Updatable getUpdatable() {
		return myUpdatable;
	}

	protected abstract double calcValue(Updatable updatable, List<Double> args);

}