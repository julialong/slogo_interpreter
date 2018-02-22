package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class LeftCommand implements Commandable {
	
	private double rotation;
	
	public LeftCommand(Argument<Double, Void> argument) {
		rotation = convertToClockwise(argument.getArg1());
		
	}

	@Override
	public Result<?> execute(Argument<?, ?> argument) {
		Turtle turtle = (Turtle) argument.getArg1();
		return new Result<Double>(turtle.rotate(rotation));
	}

	private double convertToClockwise(double rotation) {
		return (-rotation + 90) % 360;
	}

}
