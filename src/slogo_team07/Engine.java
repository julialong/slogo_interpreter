package slogo_team07;

import commands.Result;
import commands.factory.CommandFactory;
import javafx.stage.Stage;
import parser.Parser;
import view.Visualizer;

public class Engine implements ChangeListener {
	
	private static final String INIT_TURTLE = "tell [ 0 ]";
	
	private Visualizer myVis;
	private Parser myParser;

	public Engine(Stage stage) {
		myVis = new Visualizer(stage, this);
		myParser = new Parser(myVis);
		
		myParser.parse(INIT_TURTLE);
	}

	@Override
	public void changeInput(String input) {
		double ans = myParser.parse(input);
		myVis.runCommand(new Result(ans), true);
	}

	@Override
	public void changeLanguage(String lang) {
		myParser.updateLanguage(lang);
	}
}
