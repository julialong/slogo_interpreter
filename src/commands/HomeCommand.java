package commands;

public class HomeCommand extends TurtleCommand {
	
	@Override
	public Result execute() {
		return new Result(0.0);
//		Turtle turtle = getTurtle();
//		return new Result<Double>(turtle.home());
	}

}
