package commands.turtle;

import commands.Result;
import slogo_team07.Turtle;

public class LeftCommand extends TurtleCommand {
	
	private static final int NUM_ARGS = 1;
	
	private int myArgsInjected = 0;
	private double rotation;

	@Override
	public Result execute() {
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
		rotation = convertToClockwise(arg);
		myArgsInjected = 1;
	}

}
