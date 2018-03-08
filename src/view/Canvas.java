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
	private static ObservableList<String> colorList = Visualizer.possBackgroundColors;	
	
	/**
	 * @param turtles	Map of all drawables to draw, and their individual characteristics (id, active, pen color, pen width... etc)
	 */
	public Canvas(Map<Drawable, List<String>> turtles){
		myTurtles = turtles;
	}
	
	protected Pane initCanvas(){
		myPane = new Pane();
		for (Drawable turtle: myTurtles.keySet()){
			//turtle.setPane(myPane);
			turtle.getView().setFitHeight(20);
			turtle.getView().setFitWidth(20);
			myPane.getChildren().add(turtle.getView());
			List<String> properties = myTurtles.get(turtle);
			Color color = Color.valueOf(properties.get(4));
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
				turtle.getView().setFitHeight(20);
				turtle.getView().setFitWidth(20);
				myPane.getChildren().add(turtle.getView());
			}
			List<String> properties = myTurtles.get(turtle);
			List<Color> possColors = Visualizer.possPenColors;

			Color color = Color.valueOf(possColors.get(Integer.parseInt(properties.get(4))));
			double penWidth = Double.parseDouble(properties.get(5));
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
	
	protected void setColor(Color color){
		myColor = color;
	}

	protected void setPenWidth(double width){
		myPenWidth = width;
	}
	
}
