/**
 * @author Maya messinger
 * @author Jennifer Chin
 * Pop-up box that just displays all of the possible colors for canvas background and pen colors
 */

package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.keys.Resources;

public class ColorPalettes extends Group {
	Visualizer myVis;

	public ColorPalettes(Visualizer vis) {
		myVis = vis;
		Scene myScene = initPage();
		Stage myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setTitle(Resources.getString("ColorTitle"));
		myStage.show();
	}

	private Scene initPage(){
		Group root = new Group();
		root.getChildren().add(makeVBox());
		int sceneSize = 600;
		Scene scene = new Scene(root, sceneSize, Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		return scene;
	}

	private VBox makeVBox()	{
		VBox myVBox = new VBox();

		TableView bgColorTable = new TableView();
		bgColorTable.setEditable(false);
		TableColumn bgIndexCol = new TableColumn("Index");
		bgIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        bgColorTable.getColumns().add(bgIndexCol);
        TableColumn bgColorCol = new TableColumn("BackgroundColor");
		bgColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        bgColorTable.getColumns().add(bgColorCol);
        bgColorTable.setItems(myVis.bgColors);
        myVBox.getChildren().add(bgColorTable);

        TableView penColorTable = new TableView();
		penColorTable.setEditable(false);
		TableColumn penIndexCol = new TableColumn("Index");
		penIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        penColorTable.getColumns().add(penIndexCol);
        TableColumn penColorCol = new TableColumn("Pen Color");
		penColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        penColorTable.getColumns().add(penColorCol);
        penColorTable.setItems(myVis.penColors);
        myVBox.getChildren().add(penColorTable);

        return myVBox;
	}
}
