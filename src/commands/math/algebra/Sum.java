package commands.math.algebra;

import java.util.List;

import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 *
 *         This class is a leaf node in the inheritance hierarchy tree with UpdatableCommand as its
 *         direct superclass. I think it's good code mostly because of how elegant it is and how
 *         simply the concrete commands can be implemented if their superclasses are written well.
 *         There's not much to point out here, other than the fact that it pretty clearly performs a
 *         summation.
 * 
 */
public class Sum extends NonUpdatableCommand {

	private static final int NUM_ARGS = 2;

	public Sum(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}

	@Override
	protected double calcValue(List<String> args) {
		List<Double> double_args = parseToDouble(args);
		return double_args.get(0) + double_args.get(1);
	}
}