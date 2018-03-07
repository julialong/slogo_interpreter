package slogo_team07;

import java.util.HashMap;
import java.util.Map;

import commands.CommandFactory;
import commands.Commandable;
import javafx.stage.Stage;
import parser.Parser;
import view.Visualizer;

public class Engine implements ChangeListener {
	
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Visualizer myVis;
	private Parser myParser;
	private CommandFactory myCommandFactory;

	public Engine(Stage stage) {
		myVis = new Visualizer(stage, this);
		myUpdatables = new HashMap<>();
		myCommandFactory = new CommandFactory(myUpdatables, myVis);
		myParser = new Parser(myCommandFactory);
		addTurtle();
	}

	private void addTurtle() {
		Turtle turtle = new Turtle("0");
		myVis.addDrawable(turtle);
		myUpdatables.put(Integer.toString(0), turtle);
	}


	@Override
	public void changeInput(String input) {
		for (Commandable c : myParser.parse(input)) {
			c.execute();
		}
	}

	@Override
	public void changeLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
