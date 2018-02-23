package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class BackCommand extends TurtleCommand {
	
	public BackCommand() {
		super(1);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.move(args.get(0));
	}
}
