package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.keys.Resources;

public class Toolbar extends AnchorPane {
	
	private HBox myHBox;
	private Pane myCanvasObjects;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	
	public Toolbar(Pane canvas){
		myCanvasObjects = canvas;
	}
	
	public HBox initToolbar(){
		myHBox = new HBox(Resources.getInt("Inset"));
		myHBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		Text title = new Text(Resources.getString("Title"));
		title.getStyleClass().add("title");
		myHBox.getChildren().add(title);
		
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
		myHBox.getChildren().add(colorMenu);
		
		return myHBox;
	}
	
}
