/**
 * @author Maya Messinger(mm479)
 * Started 20 Feb 18
 * Dummy/test runner class for testing without integration
 */

package slogo_team07;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo_team07.Console;

public class MayaTestRunner extends Application	{

	@Override
	public void start(Stage stage) throws Exception {
        stage.setScene(dummyScene());
        stage.show();
	}

	public static void main(String[] args)	{
		launch(args);
	}

	public Scene dummyScene()	{
		Group root = new Group();

		root.getChildren().add(new Console());
		root.getChildren().add(new HelpBox());

        Scene scene = new Scene(root, 750, 750, Color.WHITE);

        return scene;
	}
}