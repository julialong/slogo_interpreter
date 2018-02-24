package commands.turtle.getters;

import java.util.List;

import commands.TurtleCommand;
import slogo_team07.Turtle;

public class GetPendown extends TurtleCommand {

	public GetPendown(Turtle turtle) {
		super(0, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.getPendown();
	}

}
