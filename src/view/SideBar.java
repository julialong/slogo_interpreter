package view;

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

public class SideBar extends Group{

	GridPane myGridPane;
	private String language = "English";
	
	public SideBar(){
		
	}
	
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
				
			}
		});
		myGridPane.add(colorMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY1"));
		
		turtleMenu.setPromptText(Resources.getString("TurtleMenu"));
		turtleMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myGridPane.add(turtleMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY2"));
		
		penMenu.setPromptText(Resources.getString("PenMenu"));
		penMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myGridPane.add(penMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY3"));
		
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
		myGridPane.add(langMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY4"));

		helpButton.setText(ResourcesLanguages.getString(language, "Help"));
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new HelpBox(language);
			}
		});
		myGridPane.add(helpButton, Resources.getInt("ButtonX"), Resources.getInt("ButtonY5"));
		
		return myGridPane;
	}
	
}
