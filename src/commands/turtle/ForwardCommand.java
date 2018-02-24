package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class ForwardCommand extends TurtleCommand {
	
	public ForwardCommand(Turtle turtle) {
		super(1, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.move(args.get(0));
	}
	
}
