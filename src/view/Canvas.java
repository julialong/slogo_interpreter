package view;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import slogo_team07.Drawable;
import commands.Result;

public class Canvas {

	private Pane myPane;
	private ArrayList<Drawable> myTurtles;
	
	public Canvas(ArrayList<Drawable> turtles){
		myTurtles = turtles;
	}
	
	public Pane initCanvas(){
		myPane = new Pane();
		for (Drawable turtle: myTurtles){ 
			turtle.getView().setFitHeight(20);
			turtle.getView().setFitWidth(20);
			myPane.getChildren().add(turtle.getView());
			turtle.draw(myPane);
		}
		return myPane;
	}
	
	public Pane updateCanvas() {		
		for (Drawable turtle: myTurtles){
			if (! turtle.getIsVisible()){
				myPane.getChildren().remove(turtle.getView());
			}
			else if (! myPane.getChildren().contains(turtle.getView())){
				turtle.getView().setFitHeight(20);
				turtle.getView().setFitWidth(20);
				myPane.getChildren().add(turtle.getView());
			}
			turtle.draw(myPane);
		}
		return myPane;
	}

	public Pane updateCanvas(Result result) {
		if (result.getRes1() == Double.MAX_VALUE)	{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Improper command");
			alert.setContentText("The command " + result.toString() + " is not supported.");
			alert.show();
		}

		return updateCanvas();
	}
	
	public void addDrawable (Drawable turtle){
		myTurtles.add(turtle);
	}
	
}
