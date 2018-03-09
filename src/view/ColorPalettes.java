/**
 * @author Maya messinger
 * @author Jennifer Chin
 * Pop-up box that just displays all of the possible colors for canvas background and pen colors
 */

package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.Resources;

public class ColorPalettes extends Group {
	private Visualizer myVis;
	private Scene myScene;
	private Group root;
	private int tableHeight = 250;
	private VBox myVBox;
	private int sceneWidth = 350;

	public ColorPalettes(Visualizer vis) {
		myVis = vis;
		myScene = initPage();
		Stage myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setTitle(Resources.getString("ColorTitle"));
		myStage.show();
	}

	private Scene initPage(){
		root = new Group();
		root.getChildren().add(makeVBox());
		Scene scene = new Scene(root, sceneWidth, Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		scroll();
		return scene;
	}

	private VBox makeVBox()	{
		myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		Text title = new Text("Color Palettes\nReference Page");
    	title.getStyleClass().add("title2");
    	myVBox.getChildren().add(title);
    	
        myVBox.getChildren().add(bgColorTable());
        myVBox.getChildren().add(penColorTable());
        myVBox.getChildren().add(iconTable());

        return myVBox;
	}

	private TableView bgColorTable()	{
		TableView bgColorTable = new TableView();
		bgColorTable.setEditable(false);
		TableColumn bgIndexCol = new TableColumn("Index");
		bgIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        bgColorTable.getColumns().add(bgIndexCol);
        TableColumn bgColorCol = new TableColumn("BackgroundColor");
		bgColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        bgColorTable.getColumns().add(bgColorCol);
        bgColorTable.setItems(myVis.bgColors);
        bgColorTable.setPrefHeight(tableHeight);

        return bgColorTable;
	}

	private TableView penColorTable()	{
		TableView penColorTable = new TableView();
		penColorTable.setEditable(false);
		TableColumn penIndexCol = new TableColumn("Index");
		penIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        penColorTable.getColumns().add(penIndexCol);
        TableColumn penColorCol = new TableColumn("Pen Color");
		penColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        penColorTable.getColumns().add(penColorCol);
        penColorTable.setItems(myVis.penColors);
        penColorTable.setPrefHeight(tableHeight);

        return penColorTable;
	}

	private TableView iconTable()	{
		TableView ivTable = new TableView();
		ivTable.setEditable(false);
		TableColumn ivIndexCol = new TableColumn("Index");
		ivIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        ivTable.getColumns().add(ivIndexCol);
        TableColumn ivCol = new TableColumn("Icon");
		ivCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        ivTable.getColumns().add(ivCol);
        ivTable.setItems(myVis.ivImages);
        ivTable.setPrefHeight(tableHeight);

        return ivTable;
	}
	
	 private void scroll(){
	    ScrollBar sc = new ScrollBar();
	    sc.setLayoutX(sceneWidth - sc.getWidth());
	    sc.setMin(0);
	    int prefHeight = 800;
	    sc.setPrefHeight(prefHeight);
	    sc.setOrientation(Orientation.VERTICAL);
	    root.getChildren().add(sc);
	        
	    int scrollScale = -1;
	    sc.valueProperty().addListener((ov, old_val, new_val) -> myVBox.setLayoutY(scrollScale * new_val.doubleValue()));
	}
}