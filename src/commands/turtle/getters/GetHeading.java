package commands.turtle.getters;

import java.util.List;

import commands.turtle.TurtleCommand;
import slogo_team07.Turtle;

public class GetHeading extends TurtleCommand {

	public GetHeading() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		return turtle.getHeading();
	}

}
