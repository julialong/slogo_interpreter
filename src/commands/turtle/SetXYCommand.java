package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class SetXYCommand extends TurtleCommand {

	public SetXYCommand() {
		super(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setPosition(args.get(0), args.get(1));
	}
}
