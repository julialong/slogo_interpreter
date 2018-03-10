/**
 * @author maya Messinger
 * Started 2 March 18
 * Window of all of the drawables on screen, and their properties
 */

package view;

import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import slogo_team07.Drawable;

public class DrawablesTable extends Group	{
	private TableView table = new TableView();
	private double colWidth = 80;
	private double numCols = 9;
	private int sceneSize = 400;
	private ObservableList<DrawableAttributes> drawables = FXCollections.observableArrayList();

	/**
	 * @param allInfo	Map of all the drawables in an instance of Visualizer, and their properties as strings
	 * @param p			pane that the drawables are displayed on
	 */
	public DrawablesTable(Map<Drawable, List<String>> allInfo, Pane p)	{
		parseInfo(allInfo, p);
		makeTable();

		Scene internal = new Scene(this, colWidth * numCols, sceneSize);
		internal.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		Stage stage = new Stage();
		stage.setScene(internal);
		stage.setTitle("All Drawables");
		stage.show();
	}

	private void parseInfo(Map<Drawable, List<String>> allInfo, Pane p)	{
		for (Map.Entry figure:allInfo.entrySet())	{
			drawables.add(new DrawableAttributes(figure, p));
		}
	}

	private Group makeTable(){
		VBox myVB = new VBox();

		table.setEditable(false);
		TableColumn turtleCol = new TableColumn("Turtle");
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
		TableColumn penUDCol = new TableColumn("Pen Down");
		penUDCol.setCellValueFactory(new PropertyValueFactory<>("penDown"));
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
}