package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class SetXYCommand extends TurtleCommand {
	
	private double myXPos;
	private double myYPos;

	public SetXYCommand(Argument argument) {
		myXPos = argument.getArg1();
		myYPos = argument.getArg2();
	}

	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.setPosition(myXPos, myYPos));
	}
}
