package commands.turtle.getters;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class GetXCor extends TurtleCommand {

	public GetXCor(Turtle turtle) {
		super(0, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.getX();
	}

}
