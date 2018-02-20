package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class ForwardCommand implements Commandable {
	
	private double delta;
	
	public ForwardCommand(Argument<Double, Void> argument) {
		this.delta = argument.getArg1();
	}

	@Override
	public <T1, T2> Result<?> execute(Argument<T1, T2> argument) {
		Turtle turtle = (Turtle) argument.getArg1();
		Double d = turtle.move(delta, 0.0);
		return new Result<Double>(d);
	}
}
