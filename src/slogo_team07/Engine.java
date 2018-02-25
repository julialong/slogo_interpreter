package slogo_team07;

import java.beans.PropertyChangeEvent;
import java.io.Console;
import java.util.Map;

import commands.CommandFactory;
import commands.Commandable;
import commands.Result;

public class Engine implements ChangeListener {

	private SlogoMain myVis;
	private Map<Integer, Updatable> myUpdatables;
	private Parser myParser;
	private CommandFactory myCommandFactory;

	public Engine() {
		myVis = new SlogoMain(this);
		addTurtle();
		myCommandFactory = new CommandFactory(myUpdatables);
		myParser = new Parser(myCommandFactory);

	}

	private void addTurtle() {
		Turtle turtle = new Turtle();
		myVis.addDrawable(turtle);
		myUpdatables.put(0, turtle);
	}

	// should be stored on the front end as PropertyChangeListener.propertyChangeInput(new ChangeListener)
	@Override
	public void changeInput(String input) {
		Iterable<Commandable> iterable = myParser.parse(input);
		for (Commandable c : iterable) {
			Result result = c.execute();
			c.updateView(result);
		}
	}

	@Override
	public void changeLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
