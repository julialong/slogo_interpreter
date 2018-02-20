package commands;

import slogo_team07.Argument;
import slogo_team07.Turtle;

public class HomeCommand implements Commandable {
	
	public HomeCommand(Argument<Void, Void> argument) {}
	
	@Override
	public <T1, T2> Result<?> execute(Argument<T1, T2> argument) {
		Turtle turtle = (Turtle) argument.getArg1();
		return new Result<Double>(turtle.move(0, 0));
	}

}
