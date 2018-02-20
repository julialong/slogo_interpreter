package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class SetXYCommand implements Commandable {
	
	private double myXPos;
	private double myYPos;

	public SetXYCommand(Argument<Double, Double> argument) {
		myXPos = argument.getArg1();
		myYPos = argument.getArg2();
	}

	@Override
	public Result<?> execute(Argument<?, ?> argument) {
		Turtle turtle = (Turtle) argument.getArg1();
		return new Result<Double>(turtle.setPosition(myXPos, myYPos));
	}
}
