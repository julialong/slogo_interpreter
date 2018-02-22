package commands.turtle;

import commands.Result;
import slogo_team07.Argument;
import slogo_team07.Turtle;

public class LeftCommand extends TurtleCommand {
	
	private double rotation;
	
	public LeftCommand(Argument argument) {
		rotation = convertToClockwise(argument.getArg1());
		
	}

	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.rotate(rotation));
	}

	private double convertToClockwise(double rotation) {
		return (-rotation + 90) % 360;
	}

}
