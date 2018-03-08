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
	private ObservableList<IndCol> bgColors = FXCollections.observableArrayList(
		new IndCol(0, Color.WHITE),
		new IndCol(1, Color.RED),
		new IndCol(2, Color.ORANGE),
		new IndCol(3, Color.YELLOW),
		new IndCol(4, Color.GREEN),
		new IndCol(5, Color.BLUE),
		new IndCol(6, Color.PURPLE),
		new IndCol(7, Color.PINK));

	private ObservableList<IndCol> penColors = FXCollections.observableArrayList(
		new IndCol(0, Color.BLACK),
		new IndCol(1, Color.WHITE),
		new IndCol(2, Color.RED),
		new IndCol(3, Color.ORANGE),
		new IndCol(4, Color.YELLOW),
		new IndCol(5, Color.GREEN),
		new IndCol(6, Color.BLUE),
		new IndCol(7, Color.PURPLE),
		new IndCol(8, Color.PINK));
	
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
        bgColorTable.setItems(bgColors);
        myVBox.getChildren().add(bgColorTable);

        TableView penColorTable = new TableView();
		penColorTable.setEditable(false);
		TableColumn penIndexCol = new TableColumn("Index");
		penIndexCol.setCellValueFactory(new PropertyValueFactory<>("ind"));
        penColorTable.getColumns().add(penIndexCol);
        TableColumn penColorCol = new TableColumn("Pen Color");
		penColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        penColorTable.getColumns().add(penColorCol);
        penColorTable.setItems(penColors);
        myVBox.getChildren().add(penColorTable);

        return myVBox;
	}

	/**
	 * Class that has properties that TableView can read in order to import into table
	 * Only public so PropertyValueFactory can get its properties
	 */
	public class IndCol	{
		private SimpleIntegerProperty ind;
		private SimpleObjectProperty color;

		private IndCol(int anInd, Color aColor)	{
			ind = new SimpleIntegerProperty(anInd);
			Shape colorBox = new Rectangle(15, 15, aColor);
			color = new SimpleObjectProperty(colorBox);
		}

		/**
		 * Returns the name of a variable, as a property
		 */
    	public IntegerProperty indProperty() {
	        return ind;
	    }

	    /**
	     * Returns the color of a variable, as a property
	     */
	    public ObjectProperty colorProperty()	{
	    	return color;
	    }
	}
}
