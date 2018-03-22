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

	/**
	 * Since these Commands take double values only in our world, you'll notice that the list 
	 * that gets passed into calcValues() is parsed to a list of doubles.  
	 */
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		Double ans = calcValues(myUpdatable, parseToDouble(getArgs()));
		visCommand(new Result(ans));
		return Double.toString(ans);
	}

	@Override
	public Updatable getUpdatable() {
		return myUpdatable;
	}

	protected abstract double calcValues(Updatable updatable, List<Double> args);
}
