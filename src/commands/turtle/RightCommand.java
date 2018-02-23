package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class RightCommand extends TurtleCommand {

	public RightCommand() {
		super(1);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.rotate(args.get(0));
	}
}
