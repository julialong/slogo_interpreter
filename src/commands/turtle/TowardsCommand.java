package commands.turtle;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class TowardsCommand extends TurtleCommand {

	public TowardsCommand(Turtle turtle) {
		super(2, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setFacing(args.get(0), args.get(1));
	}
}
