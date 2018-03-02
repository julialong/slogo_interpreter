/**
 * @author maya Messinger
 * Started 2 March 18
 * Window of all of the drawables on screen, and their properties
 */

package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import slogo_team07.Drawable;

public class DrawablesTable extends Group	{
	private VBox myVB;
	private TableView table = new TableView();
	double colWidth = 80;
	double numCols = 9;
	private ObservableList<ConvertedTurtle> drawables = FXCollections.observableArrayList();

	public DrawablesTable()	{
		// parseInfo(allInfo);
		makeTable();

		Scene internal = new Scene(this, colWidth * numCols, 400);
		Stage stage = new Stage();
		stage.setScene(internal);
		stage.setTitle("All Drawables");
		stage.show();
	}

	private void parseInfo(Map<Drawable, ArrayList<String>> allInfo)	{
	}

	private Group makeTable(){
		myVB = new VBox();

		table.setEditable(false);
		TableColumn turtleCol = new TableColumn("Turtles");
		turtleCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		turtleCol.setPrefWidth(colWidth);
		table.getColumns().add(turtleCol);
		TableColumn idCol = new TableColumn("Active");
		idCol.setCellValueFactory(new PropertyValueFactory<>("active"));
		idCol.setPrefWidth(colWidth);
		table.getColumns().add(idCol);
		TableColumn imageCol = new TableColumn("Image");
		imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
		imageCol.setPrefWidth(colWidth);
		table.getColumns().add(imageCol);
		TableColumn penUDCol = new TableColumn("Pen Up/Down");
		penUDCol.setCellValueFactory(new PropertyValueFactory<>("penUpDown"));
		penUDCol.setPrefWidth(colWidth);
		table.getColumns().add(penUDCol);
		TableColumn penColorCol = new TableColumn("Pen Color");
		penColorCol.setCellValueFactory(new PropertyValueFactory<>("penColor"));
		penColorCol.setPrefWidth(colWidth);
		table.getColumns().add(penColorCol);
		TableColumn penWidthCol = new TableColumn("Pen Width");
		penWidthCol.setCellValueFactory(new PropertyValueFactory<>("penWidth"));
		penWidthCol.setPrefWidth(colWidth);
		table.getColumns().add(penWidthCol);
		TableColumn xPosCol = new TableColumn("X Position");
		xPosCol.setCellValueFactory(new PropertyValueFactory<>("xPos"));
		xPosCol.setPrefWidth(colWidth);
		table.getColumns().add(xPosCol);
		TableColumn yPosCol = new TableColumn("Y Position");
		yPosCol.setCellValueFactory(new PropertyValueFactory<>("yPos"));
		yPosCol.setPrefWidth(colWidth);
		table.getColumns().add(yPosCol);
		TableColumn headingCol = new TableColumn("Heading");
		headingCol.setCellValueFactory(new PropertyValueFactory<>("heading"));
		headingCol.setPrefWidth(colWidth);
		table.getColumns().add(headingCol);

		table.setItems(drawables);

		myVB.getChildren().add(table);
		this.getChildren().add(myVB);

		return this;
	}

	public class ConvertedTurtle	{
		private SimpleObjectProperty id;
		private SimpleObjectProperty active;
		private SimpleObjectProperty image;
		private SimpleObjectProperty penUpDown;
		private SimpleObjectProperty penColor;
		private SimpleObjectProperty penWidth;
		private SimpleObjectProperty xPos;
		private SimpleObjectProperty yPos;
		private SimpleObjectProperty heading;

		private ConvertedTurtle(Drawable aDrawable)	{
			id = new SimpleObjectProperty("1");
			active = new SimpleObjectProperty("a");
			image = new SimpleObjectProperty("i");
			penUpDown = new SimpleObjectProperty("ud");
			penColor = new SimpleObjectProperty("c");
			penWidth = new SimpleObjectProperty("w");
			xPos = new SimpleObjectProperty("x");
			yPos = new SimpleObjectProperty("y");
			heading = new SimpleObjectProperty("h");
		}

		/**
		 * Returns this turtle's id
		 */
		public SimpleObjectProperty idProperty()	{
			return id;
		}

		/**
		 * Returns whether turtle is active or not
		 */
		public SimpleObjectProperty activeProperty()	{
			return active;
		}

		/**
		 * Returns this turtle'image
		 */
		public SimpleObjectProperty imageProperty()	{
			return image;
		}

		/**
		 * Returns whether pen for turtle is drawing a path or not
		 */
		public SimpleObjectProperty penUpDownProperty()	{
			return penUpDown;
		}

		/**
		 * Returns this turtle's pen color
		 */
		public SimpleObjectProperty penColorProperty()	{
			return penColor;
		}

		/**
		 * Returns this turtle's pen's width
		 */
		public SimpleObjectProperty penWidthProperty()	{
			return penWidth;
		}

		/**
		 * Returns this turtle's x position on canvas
		 */
		public SimpleObjectProperty xPosProperty()	{
			return xPos;
		}

		/**
		 * Returns this turtle's y position on canvas
		 */
		public SimpleObjectProperty yPosProperty()	{
			return yPos;
		}

		/**
		 * Returns this turtle's heading on canvas
		 */
		public SimpleObjectProperty headingProperty()	{
			return heading;
		}
	}
}