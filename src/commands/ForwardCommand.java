package commands;

import slogo_team07.Turtle;

public class ForwardCommand implements Commandable {
	
	private double delta;
	
	public ForwardCommand(double delta) {
		this.delta = delta;
	}

	@Override
	public double execute(Turtle turtle) {
		return turtle.move(delta, 0);
	}

}
