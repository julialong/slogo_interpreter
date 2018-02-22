package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import resources.keys.Resources;

public class SideBar extends Group{

	GridPane myGridPane;
	
	public SideBar(){
		
	}
	
	public GridPane initSideBar(){
		myGridPane = new GridPane();
		myGridPane.setPadding(new Insets(Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset")));
		myGridPane.setVgap(Resources.getInt("GridGap"));
		myGridPane.setHgap(Resources.getInt("GridGap"));
		
		ComboBox colorMenu = new ComboBox(); //observable list
		colorMenu.setPromptText(Resources.getString("ColorMenu"));
		colorMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myGridPane.add(colorMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY1"));
		
		ComboBox turtleMenu = new ComboBox(); //observable list
		turtleMenu.setPromptText(Resources.getString("TurtleMenu"));
		turtleMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myGridPane.add(turtleMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY2"));
		
		ComboBox penMenu = new ComboBox(); //observable list
		penMenu.setPromptText(Resources.getString("PenMenu"));
		penMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myGridPane.add(penMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY3"));
		
		ComboBox langMenu = new ComboBox(); //observable list
		langMenu.setPromptText(Resources.getString("LangMenu"));
		langMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				
			}
		});
		myGridPane.add(langMenu, Resources.getInt("ButtonX"), Resources.getInt("ButtonY4"));
		
		return myGridPane;
	}
	
}
