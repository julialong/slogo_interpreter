package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.Resources;
import slogo_team07.Turtle;

public class SideBar extends VBox{

	private VBox myVBox;
	private Pane myCanvasObjects;
	private ArrayList<Turtle> myTurtles;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> iconList = FXCollections.observableArrayList("Turtle", "Dog", "Cat", "Fish",
			"Octopus", "Bird", "Butterfly");
	
	public SideBar(Pane canvas, ArrayList<Turtle> turtles){
		myCanvasObjects = canvas;
		myTurtles = turtles;
	}
	
	public VBox initSideBar(){
		myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		ComboBox colorMenu = new ComboBox(colorList);
		colorMenu.setPromptText(Resources.getString("ColorMenu"));
		colorMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String tempColor = colorMenu.getSelectionModel().getSelectedItem().toString();
				myCanvasObjects.getStyleClass().removeAll("pane", "red-back", "orange-back", "yellow-back", 
						"green-back", "blue-back", "purple-back", "pink-back");
				myCanvasObjects.getStyleClass().add(Resources.getString(tempColor));
			}
		});
		myVBox.getChildren().add(colorMenu);
		
		ComboBox iconMenu = new ComboBox(iconList); //observable list
		iconMenu.setPromptText(Resources.getString("TurtleMenu"));
		iconMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String tempIcon = iconMenu.getSelectionModel().getSelectedItem().toString();
				//right now will change icon of all turtles
				for (Turtle turtle: myTurtles){
					myCanvasObjects.getChildren().remove(turtle.getView());
					turtle.setView(Resources.getString(tempIcon));
					myCanvasObjects.getChildren().add(turtle.getView());
				}
			}
		});
		myVBox.getChildren().add(iconMenu);
		
		ComboBox penMenu = new ComboBox(); //observable list
		penMenu.setPromptText(Resources.getString("PenMenu"));
		penMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myVBox.getChildren().add(penMenu);
		
		ComboBox langMenu = new ComboBox(); //observable list
		langMenu.setPromptText(Resources.getString("LangMenu"));
		langMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myVBox.getChildren().add(langMenu);
		
		return myVBox;
	}
	
}
