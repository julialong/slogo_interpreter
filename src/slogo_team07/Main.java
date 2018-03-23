package slogo_team07;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author benhubsch
 * 
 *         This class serves as nothing more than an entry point to the
 *         application.
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		new Engine(stage);
	}

	/**
	 * Runs the program
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
