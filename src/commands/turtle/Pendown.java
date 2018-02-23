package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class Pendown extends TurtleCommand {

	public Pendown() {
		super(0);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setPen(true);
	}

}
