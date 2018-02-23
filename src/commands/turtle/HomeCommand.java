package commands.turtle;

import commands.CommandArgsUnfilledException;
import commands.Result;
import slogo_team07.Turtle;

public class HomeCommand extends TurtleCommand {
	
	private static final int NUM_ARGS = 0;
	
	private int myArgsInjected = 0;
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		Turtle turtle = getTurtle();
		return new Result(turtle.home());
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {}

}
