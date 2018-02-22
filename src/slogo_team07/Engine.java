package slogo_team07;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Console;
import java.util.ArrayList;

import commands.Commandable;
import commands.Result;

public class Engine implements PropertyChangeListener {
	
	private Console myConsole;
	private ArrayList<Turtle> myTurtles;
	private Parser myParser;
	private static Engine mySingleton;
	
	private Engine() {
		myParser = new Parser();
		addTurtle();
	}
	
	public static Engine getInstance() {
        if(mySingleton == null){
        		mySingleton = new Engine();
        		myConsole = new Console(mySingleton);
        }
        return mySingleton;
    }
	
	public 

	private void addTurtle() {
		Turtle turtle = new Turtle();
		myConsole.addTurtle(turtle);
		myTurtles.add(turtle);
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
