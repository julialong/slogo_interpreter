package commands;

import slogo_team07.Turtle;

public abstract class TurtleCommand implements Commandable {
	protected Turtle getTurtle() {
		return Interpreter.getInstance().getTurtle();
	}
}
