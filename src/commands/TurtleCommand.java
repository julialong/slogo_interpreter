package commands;

import slogo_team07.Turtle;

public abstract class TurtleCommand implements Commandable {
	
	private Turtle myTurtle;
	
	protected getTurtle() {
		return Interpreter.getInstance().getTurtle();
	}
}
