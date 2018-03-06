package view;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import resources.keys.Resources;
import slogo_team07.Drawable;
import commands.Result;

public class Canvas {

	private Pane myPane;
	private Map<Drawable, List<String>> myTurtles;
	protected VBox myVBox;
	private Color myColor = Color.BLACK;
	private double myPenWidth = 1.0;
	
	
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
			turtle.draw(myPane, myColor, myPenWidth);
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
			//draw will also need to take in stroke width
			turtle.draw(myPane, myColor, myPenWidth);
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
