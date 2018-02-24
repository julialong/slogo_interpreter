package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand(Turtle turtle) {
		super(1, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setHeading(args.get(0));
	}

}
