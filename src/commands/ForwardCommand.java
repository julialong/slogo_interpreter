package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class ForwardCommand implements Commandable {
	
	private double delta;
	
	public ForwardCommand(Argument<Double, Void> argument) {
		this.delta = argument.getArg1();
	}

	@Override
	public Result<?> execute(Argument<?, ?> argument) {
		Turtle turtle = (Turtle) argument.getArg1();
		return new Result<Double>(turtle.move(delta, 0.0));
	}
}
