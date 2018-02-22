package commands.turtle;

import commands.Commandable;
import slogo_team07.Engine;
import slogo_team07.Turtle;

public abstract class TurtleCommand implements Commandable {
	protected Turtle getTurtle() {
		return Engine.getInstance().getTurtle().get(0);
	}
}
