package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class ForwardCommand implements Commandable<Turtle, Void, Double> {
	
	private double delta;
	
	public ForwardCommand(Argument<Double, Void> argument) {
		this.delta = argument.getArg1();
	}

	@Override
	public Result<Double> execute(Argument<Turtle, Void> argument) {
		Turtle turtle = argument.getArg1();
		return new Result<Double>(turtle.move(delta, 0));
	}
}
