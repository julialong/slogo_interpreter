package commands.turtle;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class BackCommand extends TurtleCommand {
	
	public BackCommand(Turtle turtle) {
		super(1, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.move(args.get(0));
	}
}
