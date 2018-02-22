package commands.turtle;

import commands.Result;
import slogo_team07.Turtle;

public class HomeCommand extends TurtleCommand {
	
	@Override
	public Result execute() {
		Turtle turtle = getTurtle();
		return new Result(turtle.home());
	}

}
