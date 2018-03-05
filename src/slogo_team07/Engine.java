package slogo_team07;

import commands.CommandFactory;
import javafx.stage.Stage;
import multiples.MultipleFactory;
import parser.Parser;
import view.Visualizer;

public class Engine implements ChangeListener {
	
	private static final String INIT_TURTLE = "tell [ 0 ]";
	
	private Visualizer myVis;
	private Parser myParser;
	private CommandFactory myCommandFactory;

	public Engine(Stage stage) {
		myVis = new Visualizer(stage, this);
		
		myCommandFactory = new CommandFactory(myVis);
		MultipleFactory mf = new MultipleFactory(myVis);
		myParser = new Parser(myCommandFactory, mf);
		
		changeInput(INIT_TURTLE);
	}

	@Override
	public void changeInput(String input) {
		myParser.parse(input);
	}

	@Override
	public void changeLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}
}
