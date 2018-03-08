package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resources.keys.Resources;
import slogo_team07.Drawable;
import commands.Result;

public class Canvas {

	private Pane myPane;
	private Map<Drawable, List<String>> myTurtles;
	protected VBox myVBox;
//	private Color myColor = Color.BLACK;
//	private double myPenWidth = 1.0;
	private double paneX;
	private double paneY;
	private double translateX;
	private double translateY;
	private double diffX;
	private double diffY;
	
	public Canvas(Map<Drawable, List<String>> turtles){
		myTurtles = turtles;
	}
	
	protected Pane initCanvas(){
		myPane = new Pane();
		for (Drawable turtle: myTurtles.keySet()){
			//turtle.setPane(myPane);
			turtle.getView().setFitHeight(20);
			turtle.getView().setFitWidth(20);
			//myPane.getChildren().add(turtle.getView());
			List<String> properties = myTurtles.get(turtle);
			Color color = Color.valueOf(properties.get(4));
			double penWidth = Double.parseDouble(properties.get(5));
			turtle.draw(myPane, color, penWidth);
		}
		return myPane;
	}
	
	protected Pane updateCanvas(Map<Drawable, List<String>> turtles) {		
		myTurtles = turtles;
//		for (Node child: myPane.getChildren()){
//			System.out.println(child);
//		}
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
			Color color = Color.valueOf(properties.get(4));
			double penWidth = Double.parseDouble(properties.get(5));
			turtle.draw(myPane, color, penWidth);
			dragAndDrop();
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
	
	protected void dragAndDrop(){
		for (Drawable turtle: myTurtles.keySet()){
			ImageView source = turtle.getView();
			source.setOnMousePressed(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					paneX = e.getSceneX();
					paneY = e.getSceneY();
					diffX = paneX - turtle.getViewX();
					diffY = paneY - turtle.getViewY();
					translateX = ((ImageView) e.getSource()).getTranslateX();
					translateY = ((ImageView) e.getSource()).getTranslateY();
				}
			});
			
			source.setOnMouseDragged(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					double offsetX = e.getSceneX() - paneX;
					double offsetY = e.getSceneY() - paneY;
					double newTranslateX = translateX + offsetX;
					double newTranslateY = translateY + offsetY;
				}
			});
			
			source.setOnMouseReleased(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e){
					turtle.setViewX(e.getSceneX() - diffX);
					turtle.setViewY(e.getSceneY() - diffY);
				}
			});
		}
	}
	
}
