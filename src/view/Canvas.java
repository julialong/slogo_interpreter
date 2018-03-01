package view;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo_team07.Drawable;
import commands.Result;

public class Canvas {

	private Pane myPane;
	private ArrayList<Drawable> myTurtles;
	protected VBox myVBox;
	private Color myColor = Color.BLACK;
	
	public Canvas(ArrayList<Drawable> turtles){
		myTurtles = turtles;
	}
	
	public Pane initCanvas(){
		myPane = new Pane();
		for (Drawable turtle: myTurtles){ 
			//turtle.setPane(myPane);
			turtle.getView().setFitHeight(20);
			turtle.getView().setFitWidth(20);
			myPane.getChildren().add(turtle.getView());
			turtle.draw(myPane, myColor);
		}
		return myPane;
	}
	
	public Pane updateCanvas(ArrayList<Drawable> turtles) {		
		myTurtles = turtles;
		for (Drawable turtle: myTurtles){
			if (! turtle.getIsVisible()){
				myPane.getChildren().remove(turtle.getView());
			}
			else if (! myPane.getChildren().contains(turtle.getView())){
				turtle.getView().setFitHeight(20);
				turtle.getView().setFitWidth(20);
				myPane.getChildren().add(turtle.getView());
			}
			turtle.draw(myPane, myColor);
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

		// if (result.getRes1() == varRetVal)	{
  //           ((SideBar)myVBox).addButton(result.toString());
  //       }

  //       if (result.getRes1() == udcRetVal)   {
  //           ((SideBar)myVBox).addButton(result.toString());
		// }

		return updateCanvas(myTurtles);
	}
	
//	public void addDrawable (Drawable turtle){
//		myTurtles.add(turtle);
//	}
	
	public void setColor(Color color){
		myColor = color;
	}
	
}
