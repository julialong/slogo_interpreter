package commands.turtle;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class HideTurtle extends TurtleCommand {

	public HideTurtle(Turtle turtle) {
		super(0, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setVisible(false);
	}

}
