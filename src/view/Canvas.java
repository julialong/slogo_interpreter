/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Visual area of screen that displays the events of commands that affect turtle
 */

package view;

import java.util.ArrayList;
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
	private double paneX;
	private double paneY;
	private double translateX;
	private double translateY;
	private double diffX;
	private double diffY;
	private int propertiesIDInd = 0;
	private int propertiesActiveInd = 1;
	private int propertiesImageInd = 2;
	private int propertiesPenDownInd = 3;
	private int propertiesColorInd = 4;
	private int propertiesPenWidthInd = 5;
	
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
			Color color = Color.valueOf(properties.get(propertiesColorInd));
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
				int turtleImgDim = 20;
				turtle.getView().setFitHeight(turtleImgDim);
				turtle.getView().setFitWidth(turtleImgDim);
				myPane.getChildren().add(turtle.getView());
			}
			List<String> properties = myTurtles.get(turtle);
			List<String> possColors = Visualizer.possPenColors;

			String shape = properties.get(propertiesImageInd);
			if (! shape.equals(Visualizer.possIVImages.get((int) turtle.getShape()))){
				properties.add(propertiesImageInd, (Visualizer.possIVImages.get((int) turtle.getShape())));
			}
			String down = properties.get(propertiesPenDownInd);
			if (Boolean.parseBoolean(down) != turtle.getIsDown()){
				properties.add(propertiesPenDownInd, Boolean.toString(turtle.getIsDown()));
			}
			//may throw error if user tries to use user-defined color from set palette command
			List<Color> colors = turtle.getMyColors();
			Color color = colors.get((int) Double.parseDouble(properties.get(propertiesColorInd)));
			if (! color.equals(colors.get((int) turtle.getPenColor()))){
				color = colors.get((int) turtle.getPenColor());
				properties.add(propertiesColorInd, Double.toString(turtle.getPenColor()));
			}
			double penWidth = Double.parseDouble(properties.get(propertiesPenWidthInd));
			if (penWidth != turtle.getPenWidth()){
				penWidth = turtle.getPenWidth();
				properties.add(propertiesPenWidthInd, Double.toString(penWidth));
			}
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
