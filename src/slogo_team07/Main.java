package slogo_team07;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		 Engine engine = new Engine(stage);
	}
	
	
	/**
	 * Runs the program
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
