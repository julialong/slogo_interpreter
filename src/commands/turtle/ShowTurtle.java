package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class ShowTurtle extends TurtleCommand {

	public ShowTurtle() {
		super(0);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.setVisible(true);
	}

}
