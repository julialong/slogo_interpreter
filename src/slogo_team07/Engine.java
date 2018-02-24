package slogo_team07;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Console;
import java.util.ArrayList;

import commands.Commandable;
import commands.Result;

public class Engine implements PropertyChangeListener {
	
	private Console myConsole;
	private ArrayList<Updatable> myUpdatables;
	private Parser myParser;
	private static Engine mySingleton;
	
	public Engine() {
		myConsole = new Console(this);
		addTurtle();
		myParser = new Parser(new CommandFactory(myTurtles));
		
	}

	private void addTurtle() {
		Turtle turtle = new Turtle();
		myConsole.addDrawable(turtle);
		myUpdatables.add(turtle);
	}

	// should be stored on the front end as PropertyChangeListener.propertyChange(new ChangeListener)	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		Iterable iterable = myParser.parse(event.getNewValue());
		for (Commandable c : iterable) {
			Result result = c.execute();
			c.updateView(result);
		}
	}
}
