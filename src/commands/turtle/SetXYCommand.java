package commands.turtle;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Result;
import slogo_team07.Turtle;

public class SetXYCommand extends TurtleCommand {
	
	private static final int NUM_ARGS = 2;
	
	private int myArgsInjected = 0;
	private double myXPos;
	private double myYPos;

	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		Turtle turtle = getTurtle();
		return new Result(turtle.setPosition(myXPos, myYPos));
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}
		
		if (myArgsInjected == 0) {
			myXPos = arg;
		} else {
			myYPos = arg;
		}
		myArgsInjected += 1;
	}
}
