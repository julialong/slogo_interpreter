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
	
	public void addDrawable (Drawable turtle){
		myTurtles.add(turtle);
	}
	
}
