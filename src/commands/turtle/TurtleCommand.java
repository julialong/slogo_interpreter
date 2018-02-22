package commands.turtle;

import slogo_team07.Turtle;
import commands.Commandable;

public abstract class TurtleCommand implements Commandable {
	protected Turtle getTurtle() {
		return Interpreter.getInstance().getTurtle();
	}
}
