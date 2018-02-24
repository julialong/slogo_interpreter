package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;

public class SideBar extends VBox{
	private VBox myVBox;
	private Pane myCanvasObjects;
	private ArrayList<Turtle> myTurtles;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> iconList = FXCollections.observableArrayList("Turtle", "Dog", "Cat", "Fish",
			"Octopus", "Bird", "Butterfly");
	GridPane myGridPane;
	private String language = "English";ic
	
	public SideBar(Pane canvas, ArrayList<Turtle> turtles){
		myCanvasObjects = canvas;
		myTurtles = turtles;
	}
	
	public VBox initSideBar(){
		myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		ComboBox colorMenu = new ComboBox(colorList);

	public GridPane initSideBar(){
		myGridPane = new GridPane();
		myGridPane.setPadding(new Insets(Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset")));
		myGridPane.setVgap(Resources.getInt("GridGap"));
		myGridPane.setHgap(Resources.getInt("GridGap"));

		ComboBox colorMenu = new ComboBox();
		ComboBox turtleMenu = new ComboBox();
		ComboBox penMenu = new ComboBox(); //observable list
		ComboBox langMenu = new ComboBox<String>(); //observable list
		Button helpButton = new Button();

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
		
		penMenu.setPromptText(Resources.getString("PenMenu"));
		penMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myVBox.getChildren().add(penMenu);
		
		langMenu.setPromptText(Resources.getString("LangMenu"));
		langMenu.getItems().addAll(
			"Chinese",
			"English",
			"French",
			"German",
			"Italian",
			"Portuguese",
			"Russian",
			"Spanish"
			);
		langMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				language = (String)(langMenu.getValue());
				helpButton.setText(ResourcesLanguages.getString(language, "Help"));
			}
		});
		myVBox.getChildren().add(langMenu);

		helpButton.setText(ResourcesLanguages.getString(language, "Help"));
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new HelpBox(language);
			}
		});
		myGridPane.add(helpButton, Resources.getInt("ButtonX"), Resources.getInt("ButtonY5"));
		
		return myVBox;
	}
	
}
