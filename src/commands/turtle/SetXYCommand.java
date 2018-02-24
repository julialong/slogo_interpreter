package commands.turtle;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class SetXYCommand extends TurtleCommand {

	public SetXYCommand(Turtle turtle) {
		super(2, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setPosition(args.get(0), args.get(1));
	}
}
