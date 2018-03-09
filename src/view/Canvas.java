/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Visual area of screen that displays the events of commands that affect turtle
 */

package view;

import java.util.List;
import java.util.Map;

import commands.Result;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo_team07.Drawable;

public class Canvas {
	private Pane myPane;
	private Map<Drawable, List<String>> myTurtles;
	protected VBox myVBox;
	private int propertiesIDInd = 0;
	private int propertiesActiveInd = 1;
	private int propertiesImageInd = 2;
	private int propertiesPenDownInd = 3;
	private int propertiesColorInd = 4;
	private int propertiesPenWidthInd = 5;
	
	/**
	 * @param turtles	Map of all drawables to draw, and their individual characteristics (id, active, pen color, pen width... etc)
	 */
	public Canvas(Map<Drawable, List<String>> turtles){
		myTurtles = turtles;
	}
	
	protected Pane initCanvas(){
		myPane = new Pane();
		for (Drawable turtle: myTurtles.keySet()){
			int turtleImgDim = 20;
			turtle.getView().setFitHeight(turtleImgDim);
			turtle.getView().setFitWidth(turtleImgDim);
			myPane.getChildren().add(turtle.getView());
			List<String> properties = myTurtles.get(turtle);
			Color color = Color.valueOf(properties.get(propertiesColorInd));
			double penWidth = turtle.getPenWidth();
			turtle.draw(myPane, color, penWidth);
		}
		return myPane;
	}
	
	protected Pane updateCanvas(Map<Drawable, List<String>> turtles) {		
		myTurtles = turtles;
		for (Drawable turtle: myTurtles.keySet()){
			if (! turtle.getIsVisible()){
				myPane.getChildren().remove(turtle.getView());
			}
			else if (! myPane.getChildren().contains(turtle.getView())){
				int turtleImgDim = 20;
				turtle.getView().setFitHeight(turtleImgDim);
				turtle.getView().setFitWidth(turtleImgDim);
				myPane.getChildren().add(turtle.getView());
			}
			List<String> properties = myTurtles.get(turtle);
			List<String> possColors = Visualizer.possPenColors;

			Color color = Color.valueOf(possColors.get(Integer.parseInt(properties.get(propertiesColorInd))));
			double penWidth = Double.parseDouble(properties.get(propertiesPenWidthInd));
			turtle.draw(myPane, color, penWidth);
		}
		return myPane;
	}

	protected Pane updateCanvas(Result result) {
		if (result.getRes1() == Double.MAX_VALUE)	{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Improper command");
			alert.setContentText("The command " + result.toString() + " is not supported.");
			alert.show();
		}
		return updateCanvas(myTurtles);
	}
	
}
