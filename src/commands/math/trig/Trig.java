package commands.math.trig;

import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 * This is the abstract superclass for Trigonometric commands, which
 * all only take a single argument.  
 *
 */
public abstract class Trig extends NonUpdatableCommand {
	
	private static final int NUM_ARGS = 1;

	public Trig(Visualizer vis, VariableReplacer var_replacer) {
		super(vis, var_replacer, NUM_ARGS);
	}
}
