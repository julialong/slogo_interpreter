package slogo_team07;

import java.util.Map;
import java.util.HashMap;

import view.Visualizer;
import model.Parser;
import commands.CommandFactory;
import commands.Commandable;
import commands.Result;

public class Engine implements ChangeListener {

	private Visualizer myVis;
	private Map<String, Updatable> myUpdatables;
	private Parser myParser;
	private CommandFactory myCommandFactory;

	public Engine(Visualizer vis) {
		myVis = vis;
		myUpdatables = new HashMap<>();
		myCommandFactory = new CommandFactory(myUpdatables);
		myParser = new Parser(myCommandFactory);
		addTurtle();
	}

	private void addTurtle() {
		Turtle turtle = new Turtle();
		myVis.addDrawable(turtle);
		myUpdatables.put(Integer.toString(0), turtle);
	}

	// should be stored on the front end as PropertyChangeListener.propertyChangeInput(new ChangeListener)
	@Override
	public void changeInput(String input) {
		Iterable<Commandable> iterable = myParser.parse(input);
		for (Commandable c : iterable) {
			Result result = c.execute();
			myVis.updateCanvas(result);
		}
	}

	@Override
	public void changeLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
