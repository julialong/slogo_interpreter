package commands.turtle;

import commands.Result;
import slogo_team07.Turtle;

public class SetXYCommand extends TurtleCommand {
	
	private static final int NUM_ARGS = 2;
	
	private int myArgsInjected = 0;
	private double myXPos;
	private double myYPos;

	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.setPosition(myXPos, myYPos));
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {
		
	}
}
