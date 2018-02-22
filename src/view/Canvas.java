package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resources.keys.Resources;
import slogo_team07.Turtle;

public class Canvas {

	private GridPane myPane;
	
	public Canvas(){
		
	}
	
	public GridPane initCanvas(ArrayList<Turtle> turtles){
		myPane = new GridPane();
		myPane.setPadding(new Insets(Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset")));
		myPane.setVgap(Resources.getInt("GridGap"));
		myPane.setHgap(Resources.getInt("GridGap"));
		Turtle test = new Turtle(20, 20);
//		for (Turtle turtle: turtles){
//			turtle.draw();
//		}
		//myPane.add(new Rectangle(100, 100, Color.BLACK), 100, 100);
		test.myIV.setFitHeight(10);
		test.myIV.setFitWidth(10);
		myPane.add(test.myIV, 10, 10);
		return myPane;
	}
	
}
