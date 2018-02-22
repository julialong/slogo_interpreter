package commands.turtle;

import commands.Result;
import slogo_team07.Argument;
import slogo_team07.Turtle;

public class ForwardCommand extends TurtleCommand {
	
	private double delta;
	
	public ForwardCommand(Argument argument) {
		delta = argument.getArg1();
	}

	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.move(delta));
	}
}
