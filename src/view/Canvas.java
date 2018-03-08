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
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resources.keys.Resources;
import slogo_team07.Drawable;

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
			int propertiesIDInd = 0;
			int propertiesActiveInd = 1;
			int propertiesImageInd = 2;
			int propertiesPenDownInd = 3;
			int propertiesColorInd = 4;
			int propertiesPenWidthInd = 5;
			Color color = Color.valueOf(properties.get(propertiesColorInd));
			double penWidth = turtle.getPenWidth();
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
