package slogo_team07;

import javafx.stage.Stage;
import parser.Parser;
import view.Visualizer;

public class Engine implements ChangeListener {
	
	private Visualizer myVis;
	private Parser myParser;
	private TurtleManager myTurtleManager;

	public Engine(Stage stage) {
		myVis = new Visualizer(stage, this);
		myTurtleManager = new TurtleManager(myVis, this);
		myParser = new Parser(myTurtleManager);
		addTurtle();
	}

	private void addTurtle() {
		Turtle turtle = new Turtle();
		myVis.addDrawable(turtle);
		myTurtleManager.addUpdatable(turtle);
	}

	@Override
	public void changeInput(String input) {
		double useless = myParser.parse(input);
	}

	@Override
	public void changeLanguage(String lang) {
		myTurtleManager.updateLanguage(lang);
	}
}
