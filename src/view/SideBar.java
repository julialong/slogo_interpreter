/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Started 20 Feb 18
 * Class that holds all of the GUI sidebar items - mmostly menus
 */

package view;

import java.util.List;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.Resources;
import slogo_team07.Drawable;
import slogo_team07.Turtle;

import resources.languages.ResourcesLanguages;

public class SideBar extends VBox{
	private VBox myVBox;
	private Pane myCanvasObjects;
	private List<Drawable> myTurtles;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> iconList = FXCollections.observableArrayList("Turtle", "Dog", "Cat", "Fish",
			"Octopus", "Bird", "Butterfly");
	private ObservableList<String> langsSupported = FXCollections.observableArrayList("Chinese", "English",
			"French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	List<Button> uDefCommands = new ArrayList<>();
	protected TextInput myTextInput;
	protected String language;
	
	/**
	 * Constructor for sidebar of program that contains buttons/options
	 * @param canvas	canvas of program, where turtles are displayed
	 * @param turtles	list of all the movers in the canvas
	 */
	public SideBar(Pane canvas, List<Drawable> turtles){
		myCanvasObjects = canvas;
		myTurtles = turtles;
	}
	
	/**
	 * Creates the sidebar as a VBox and adds its children (buttons) inside
	 */
	public VBox initSideBar(){
		myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		Button helpButton = new Button();
		ComboBox colorMenu = new ComboBox(colorList);
		ComboBox iconMenu = new ComboBox(iconList); //observable list
		ComboBox penMenu = new ComboBox(); //observable list
		ComboBox langMenu = new ComboBox<String>(langsSupported); //observable list
		TableView table = new TableView();

		helpButton.setText(ResourcesLanguages.getString(language, "Help"));
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new HelpBox(language);
			}
		});
		myVBox.getChildren().add(helpButton);

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
		
		iconMenu.setPromptText(Resources.getString("TurtleMenu"));
		iconMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String tempIcon = iconMenu.getSelectionModel().getSelectedItem().toString();
				//right now will change icon of all turtles
				for (Drawable turtle: myTurtles){
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
		langMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				language = (String)(langMenu.getValue());
				helpButton.setText(ResourcesLanguages.getString(language, "Help"));
			}
		});
		myVBox.getChildren().add(langMenu);

		table.setEditable(true);
		TableColumn udcs = new TableColumn("User-defined commands");
		TableColumn vars = new TableColumn("Variables");
		TableColumn varName = new TableColumn("Name");
		TableColumn varValue = new TableColumn("Value");
		vars.getColumns().addAll(varName, varValue);
        table.getColumns().addAll(udcs, vars);
		myVBox.getChildren().add(table);
				
		return myVBox;
	}

	protected void addButton(String text)	{
		if (buttonExists(text))	{
			return;
		}

		Button udc = new Button();
		udc.setText(text);
		uDefCommands.add(udc);

		udc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				myTextInput.loadInput("\n" + udc.getText());
			}
		});

		myVBox.getChildren().add(udc);
	}

	protected void addVar(String var, String value)	{

	}

	private boolean buttonExists(String text)	{
		for (Button butt:uDefCommands)	{
			if (butt.getText().equals(text))	{
				return true;
			}
		}

		return false;
	}
}
