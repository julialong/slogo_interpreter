package view;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import slogo_team07.Drawable;
import slogo_team07.Turtle;

public class Canvas {

	private Pane myPane;
	private ArrayList<Drawable> myTurtles;
	
	public Canvas(ArrayList<Drawable> turtles){
		myTurtles = turtles;
	}
	
	public Pane initCanvas(){
		myPane = new Pane();
		for (Drawable turtle: myTurtles){ 
			//hardcoded need to implement draw function
			turtle.getView().setFitHeight(20);
			turtle.getView().setFitWidth(20);
			myPane.getChildren().add(turtle.getView());
		}
		return myPane;
	}
	
	public void addDrawable (Drawable turtle){
		
	}
	
}
