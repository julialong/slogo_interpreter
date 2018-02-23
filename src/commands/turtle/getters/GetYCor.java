package commands.turtle.getters;

import java.util.List;

import commands.turtle.TurtleCommand;
import slogo_team07.Turtle;

public class GetYCor extends TurtleCommand {

	public GetYCor() {
		super(0);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.getY();
	}

}
