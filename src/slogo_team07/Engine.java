package slogo_team07;

import java.util.HashMap;
import java.util.Map;

import commands.CommandFactory;
import commands.Commandable;
import commands.Result;
import view.SlogoMain;

public class Engine implements ChangeListener {
	
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private SlogoMain myVis;
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

	// should be stored on the front end as ChangeListener.changeInput(String)	
	@Override
	public void changeInput(String input) {
		Iterable<Commandable> iterable = myParser.parse(input);
		for (Commandable c : iterable) {
			Result result = c.execute();
			myVis.updateView(result);
		}
	}

	@Override
	public void changeLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
