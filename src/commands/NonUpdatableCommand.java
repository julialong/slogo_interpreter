package commands;

import java.util.List;

import commands.factory.VariableReplacer;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 * This class is for commands that don't touch the Updatable: i.e., they don't act on the turtle at all.
 * It inherits from Command because it is a more specific kind of Command.
 *
 */
public abstract class NonUpdatableCommand extends Command {

	public NonUpdatableCommand(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		super(vis, var_replacer, num_args);
	}
	
	/**
	 * This function gets the arguments from its superclass and passes them allong to be calculated
	 * by the concrete instance of a NonUpdatableCommand object.
	 */
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(replaceVars(getArgs()));
		visCommand(new Result(ans));
		return Double.toString(ans);
	}
	
	protected abstract double calcValue(List<String> args);
}
