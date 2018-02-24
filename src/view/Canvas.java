package view;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import slogo_team07.Turtle;

public class Canvas {

	private Pane myPane;
	
	public Canvas(){
		
	}
	
	public Pane initCanvas(ArrayList<Turtle> turtles){
		myPane = new Pane();
		for (Turtle turtle: turtles){ 
			//hardcoded need to implement draw function
			turtle.getView().setFitHeight(20);
			turtle.getView().setFitWidth(20);
			myPane.getChildren().add(turtle.getView());
		}
		return myPane;
	}
	
}
