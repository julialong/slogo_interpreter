package commands.turtle;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Result;
import slogo_team07.Turtle;

public class LeftCommand extends TurtleCommand {
	
	private static final int NUM_ARGS = 1;
	
	private int myArgsInjected = 0;
	private double rotation;

	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		Turtle turtle = getTurtle();
		return new Result(turtle.rotate(rotation));
	}

	private double convertToClockwise(double rotation) {
		return (-rotation + 90) % 360;
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
		
		rotation = convertToClockwise(arg);
		myArgsInjected = 1;
	}

}
