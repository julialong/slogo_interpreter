package commands.turtle.getters;

import java.util.List;

import commands.turtle.TurtleCommand;
import slogo_team07.Turtle;

public class GetPendown extends TurtleCommand {

	public GetPendown() {
		super(0);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.getPendown();
	}

}
