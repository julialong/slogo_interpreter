package commands;

import slogo_team07.Turtle;

public class HomeCommand implements Commandable {

	@Override
	public double execute(Turtle turtle) {
		return turtle.move(0, 0);
	}

}
