package commands.turtle;

import commands.Result;
import slogo_team07.Argument;
import slogo_team07.Turtle;

public class RightCommand extends TurtleCommand {
	
	private double rotation;
	
	public RightCommand(Argument argument) {
		rotation = argument.getArg1();
	}

	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.rotate(rotation));
	}
}
