package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class HomeCommand extends TurtleCommand {

	public HomeCommand(int num_args) {
		super(0);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.home();
	}

}
