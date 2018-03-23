package commands.booleans;

import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 *         This is a superclass for all Boolean commands. It implements a
 *         function that converts all boolean objects to their double
 *         equivalents.
 *
 */
public abstract class Boolean extends NonUpdatableCommand {

	public Boolean(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		super(vis, var_replacer, num_args);
	}

	protected double boolToDouble(boolean bool) {
		return (bool) ? 1.0 : 0.0;
	}

}
