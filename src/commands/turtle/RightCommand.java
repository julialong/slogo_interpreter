package commands.turtle;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class RightCommand extends TurtleCommand {

	public RightCommand(Turtle turtle) {
		super(1, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.rotate(args.get(0));
	}
}
