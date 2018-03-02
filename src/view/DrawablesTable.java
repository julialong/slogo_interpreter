/**
 * @author maya Messinger
 * Started 2 March 18
 * Window of all of the drawables on screen, and their properties
 */

package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DrawablesTable extends Group	{
	private VBox myVB;
	private TableView table = new TableView();
	private ObservableList<ConvertedTurtle> drawables = FXCollections.observableArrayList();

	public DrawablesTable(Map<Drawable, ArrayList<String>> allInfo)	{
		parseInfo(allInfo);
		makeTable();
	}

	private void parseInfo(Map<Drawable, ArrayList<String>> allInfo)	{
	}

	private Group makeTable(){
		myVB = new VBox();

		double colWidth = 50;

		table.setEditable(false);
		turtleCol = new TableColumn("Turtles");
		turtleCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		turtleCol.setPrefWidth(colWidth);
		table.getColumns().add(turtleCol);
		idCol = new TableColumn("Active");
		idCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		idCol.setPrefWidth(colWidth);
		table.getColumns().add(idCol);
		imageCol = new TableColumn("Image");
		imageCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		imageCol.setPrefWidth(colWidth);
		table.getColumns().add(imageCol);
		penUDCol = new TableColumn("Pen Up/Down");
		penUDCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		penUDCol.setPrefWidth(colWidth);
		table.getColumns().add(penUDCol);
		penColorCol = new TableColumn("Pen Color");
		penColorCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		penColorCol.setPrefWidth(colWidth);
		table.getColumns().add(penColorCol);
		penWidthCol = new TableColumn("Pen Width");
		penWidthCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		penWidthCol.setPrefWidth(colWidth);
		table.getColumns().add(penWidthCol);
		xPosCol = new TableColumn("X Position");
		xPosCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		xPosCol.setPrefWidth(colWidth);
		table.getColumns().add(xPosCol);
		yPosCol = new TableColumn("Y Position");
		yPosCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		yPosCol.setPrefWidth(colWidth);
		table.getColumns().add(yPosCol);
		headingCol = new TableColumn("Heading");
		headingCol.setCellValueFactory(new PropertyValueFactory<>("_____"));
		headingCol.setPrefWidth(colWidth);
		table.getColumns().add(headingCol);

		table.setItems(drawables);

		myVBox.getChildren().add(table);
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

		private LoadButton()	{
			super();
			me = new SimpleObjectProperty(this);
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