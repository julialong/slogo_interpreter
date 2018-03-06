package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;

public class Toolbar extends AnchorPane {
	
	private AnchorPane myPane;
	private HBox myHBox;
	private Pane myCanvasObjects;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> langsSupported = FXCollections.observableArrayList("Chinese", "English",
			"French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	private String myLanguage;
	
	public Toolbar(Pane canvas){
		myCanvasObjects = canvas;
	}
	
	public AnchorPane initToolbar(){
		myPane = new AnchorPane();
		myHBox = new HBox(Resources.getInt("Inset"));
		myHBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		Text title = new Text(Resources.getString("Title"));
		title.getStyleClass().add("title");
		myPane.getChildren().add(title);
		myPane.setLeftAnchor(title, 10.0);
		myPane.setTopAnchor(title, 10.0);
		
		Button helpButton = helpButton();
		myHBox.getChildren().add(helpButton);
		myHBox.getChildren().add(colorMenu());
		myHBox.getChildren().add(langMenu(helpButton));
		myHBox.getChildren().add(windowButton());
		myHBox.getChildren().add(saveButton());
		myHBox.getChildren().add(loadButton());
		
		myPane.getChildren().add(myHBox);
		myPane.setRightAnchor(myHBox, 10.0);
		
		return myPane;
	}
	
	private Button helpButton()	{
		Button helpButton = new Button();
		helpButton.setText(ResourcesLanguages.getString(myLanguage, "Help"));
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new HelpBox(myLanguage);
			}
		});
    	return helpButton;
    }
	
	private ComboBox colorMenu()	{
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
		return colorMenu;
	}
	
	private ComboBox langMenu(Button helpButton)	{
		ComboBox langMenu = new ComboBox<String>(langsSupported);
		langMenu.setPromptText(Resources.getString("LangMenu"));
		langMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				myLanguage = (String)(langMenu.getValue());
				helpButton.setText(ResourcesLanguages.getString(myLanguage, "Help"));
			}
		});
		return langMenu;
	}
	
	//need to write
	private Button windowButton()	{
		Button windowButton = new Button("New Window");
    	windowButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return windowButton;
    }
	
	//need to sync w parser
	private Button saveButton()	{
		Button saveButton = new Button("Save");
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return saveButton;
    }
	
	//need to sync w parser
	private Button loadButton()	{
		Button loadButton = new Button("Load");
    	loadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return loadButton;
    }
	
	public void setLanguage(String lang){
		myLanguage = lang;
	}
	
	public String getLanguage(){
		return myLanguage;
	}
	
}
