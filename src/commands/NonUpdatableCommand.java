package commands;

import java.util.List;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
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
	
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(replaceVars(getArgs()));
		visCommand(new Result(ans));
		return Double.toString(ans);
	}
	
	@Override
	public boolean hasUpdatable() {
		return false;
	}
	
	@Override
	public Updatable getUpdatable() {
		return null;
	}
	
	
	protected abstract double calcValue(List<String> args);
}
