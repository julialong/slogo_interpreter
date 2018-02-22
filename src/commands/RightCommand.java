package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class RightCommand extends TurtleCommand {
	
	private double rotation;
	
	public RightCommand(Argument argument) {
		rotation = argument.getArg1();
	}

	@Override
	public Result execute() {
		return new Result(0.0);
//		Turtle turtle = getTurtle();
//		return new Result(turtle.rotate(rotation));
	}
}
