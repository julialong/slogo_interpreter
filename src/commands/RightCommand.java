package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class RightCommand implements Commandable {
	
	private double rotation;
	
	public RightCommand(Argument<Double, Void> argument) {
		rotation = argument.getArg1();
	}

	@Override
	public Result<?> execute(Argument<?, ?> argument) {
		Turtle turtle = (Turtle) argument.getArg1();
		return new Result<Double>(turtle.rotate(rotation));
	}
}
