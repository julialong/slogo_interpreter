package commands;

import slogo_team07.Turtle;

public class ForwardCommand implements Commandable {
	
	private double delta;
	
	public ForwardCommand(double delta) {
		this.delta = delta;
	}

	@Override
	public void execute(Turtle turtle) {
		turtle.move(delta, 0);
	}

}
