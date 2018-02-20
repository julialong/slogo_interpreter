package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class HomeCommand implements Commandable<Turtle, Void, Double> {
	
	public HomeCommand(Argument<Void, Void> argument) {}

	@Override
	public Result<Double> execute(Argument<Turtle, Void> argument) {
		Turtle turtle = argument.getArg1();
		return new Result<Double>(turtle.move(0, 0));
	}

}
