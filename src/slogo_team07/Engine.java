package slogo_team07;

import java.util.HashMap;
import java.util.Map;

import commands.CommandFactory;
import commands.Commandable;
import commands.Result;
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
		myCommandFactory = new CommandFactory(myUpdatables);
		myParser = new Parser(myCommandFactory);
		addTurtle();
	}

	private void addTurtle() {
		Turtle turtle = new Turtle();
		myVis.addDrawable(turtle);
		myUpdatables.put(Integer.toString(0), turtle);
	}


	@Override
	public void changeInput(String input) {
		System.out.println(input);
		for (Commandable c : myParser.parse(input)) {
			Result result = c.execute();
			myVis.runCommand(result);

		}
	}

	@Override
	public void changeLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
