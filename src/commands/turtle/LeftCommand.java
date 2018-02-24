package commands.turtle;

import java.util.List;

import slogo_team07.Turtle;

public class LeftCommand extends TurtleCommand {

	public LeftCommand(Turtle turtle) {
		super(0, turtle);
	}

	@Override
	protected Double calcValues(Turtle turtle, List<Double> args) {
		Double clockwise = convertToClockwise(args.get(0));
		return turtle.rotate(clockwise);
	}
	
	private Double convertToClockwise(double rotation) {
		return (-rotation + 90) % 360;
	}


}
