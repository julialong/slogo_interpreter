package commands.turtle;

import commands.Result;
import slogo_team07.Turtle;

public class ForwardCommand extends TurtleCommand {
	
	private static final int NUM_ARGS = 1;
	
	private int myArgsInjected = 0;
	private double myDelta;
	
	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.move(myDelta));
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {
		myDelta = arg;
		myArgsInjected = 1;
	}
}
