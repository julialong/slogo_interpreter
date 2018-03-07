package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.keys.Resources;

public class ColorPalettes extends Group {
	
	public ColorPalettes() {
		Scene myScene = initPage();
		Stage myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setTitle(Resources.getString("ColorTitle"));
		myStage.show();
	}

	private Scene initPage(){
		Group root = new Group();
		Scene scene = new Scene(root, 600, Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		return scene;
	}
}
