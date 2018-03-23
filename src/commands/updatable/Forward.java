package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 *         This class is a leaf node in the inheritance hierarchy tree with UpdatableCommand as its
 *         direct superclass. I think it's good code mostly because of how elegant it is and how
 *         simply the concrete commands can be implemented if their superclasses are written well.
 *         There's not much to point out here, other than the fact that it delegates to the
 *         Updatable object to actually move itself, allowing that class to be more active.
 * 
 */
public class Forward extends UpdatableCommand {

	private static final int NUM_ARGS = 1;

	public Forward(Visualizer vis, VariableReplacer var_replacer, Updatable updatable) {
		super(vis, var_replacer, NUM_ARGS, updatable);
	}

	@Override
	protected double calcValue(Updatable updatable, List<Double> args) {
		return updatable.move(args.get(0));
	}
}