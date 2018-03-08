/**
 * @author Maya messinger
 * @author Jennifer Chin
 * Pop-up box that just displays all of the possible colors for canvas background and pen colors
 */

package view;

import java.util.List;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
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
		root.getChildren().add(makeVBox());
		Scene scene = new Scene(root, 600, Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
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
        bgColorTable.setItems(Visualizer.bgColors);
        myVBox.getChildren().add(bgColorTable);

        TableView penColorTable = new TableView();
		penColorTable.setEditable(false);
		TableColumn penIndexCol = new TableColumn("Index");
		penIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        penColorTable.getColumns().add(penIndexCol);
        TableColumn penColorCol = new TableColumn("Pen Color");
		penColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        penColorTable.getColumns().add(penColorCol);
        penColorTable.setItems(Visualizer.penColors);
        myVBox.getChildren().add(penColorTable);

        return myVBox;
	}
}
