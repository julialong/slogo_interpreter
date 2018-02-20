package commands;

import slogo_team07.Turtle;

public class NullCommand implements Commandable {

	@Override
	public double execute(Turtle turtle) {
		// do nothing	
		return 0.0;
	}

}
