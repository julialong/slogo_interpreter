package commands;

import slogo_team07.Argument;

public class LeftCommand extends TurtleCommand {
	
	private double rotation;
	
	public LeftCommand(Argument argument) {
		rotation = convertToClockwise(argument.getArg1());
		
	}

	@Override
	public Result execute() {
		return new Result(0.0);
//		Turtle turtle = getTurtle();
//		return new Result(turtle.rotate(rotation));
	}

	private double convertToClockwise(double rotation) {
		return (-rotation + 90) % 360;
	}

}
