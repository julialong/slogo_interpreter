package view;

import java.io.File;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;

public class Toolbar extends AnchorPane {
	
	private AnchorPane myPane;
	private Pane myCanvasObjects;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> langsSupported = FXCollections.observableArrayList("Chinese", "English",
			"French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	private String myLanguage;
	private Visualizer myVis;
	
	public Toolbar(Pane canvas, Visualizer vis){
		myCanvasObjects = canvas;
		myVis = vis;
	}
	
	public AnchorPane initToolbar(){
		myPane = new AnchorPane();
		
		Text title = new Text(Resources.getString("Title"));
		title.getStyleClass().add("title");
		myPane.getChildren().add(title);
		myPane.setLeftAnchor(title, 10.0);
		myPane.setTopAnchor(title, 10.0);
		
		VBox myButtons = initButtons();
		myPane.getChildren().add(myButtons);
		myPane.setRightAnchor(myButtons, 0.0);
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
	
	//need to send tell command to create initial turtle on new window
	private Button windowButton()	{
		Button windowButton = new Button("New Window");
    	windowButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new Visualizer(new Stage(), myVis.getChangeListener());
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
	
	//what does julia want me to do with chosen file
	private Button loadButton()	{
		Button loadButton = new Button("Load");
    	loadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fc = new FileChooser();
				fc.setTitle(Resources.getString("ChooserTitle"));
				File file = fc.showOpenDialog(new Stage());
			}
		});
    	return loadButton;
    }
	
	private VBox initButtons(){
		HBox myHBox = new HBox(Resources.getInt("Inset"));
		Button helpButton = helpButton();
		myHBox.getChildren().add(helpButton);
		myHBox.getChildren().add(colorMenu());
		myHBox.getChildren().add(langMenu(helpButton));
		myHBox.getChildren().add(windowButton());
		myHBox.getChildren().add(saveButton());
		myHBox.getChildren().add(loadButton());
		
		HBox buttons = new HBox(Resources.getInt("Inset"));
		buttons.getChildren().add(pauseButton());
		buttons.getChildren().add(stepButton());
		buttons.getChildren().add(resetButton());
		buttons.getChildren().add(undoButton());
		buttons.getChildren().add(speedUpButton());
		buttons.getChildren().add(slowDownButton());
		buttons.getChildren().add(addDrawableButton());
		buttons.getChildren().add(colorButton());
		
		VBox myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));
		myVBox.getChildren().add(myHBox);
		myVBox.getChildren().add(buttons);
		
		return myVBox;
	}
	
	//need to link w visualizer class somehow
	private Button addDrawableButton()	{
		Button addDrawableButton = new Button("Add Turtle");
	    addDrawableButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			}
		});
		return addDrawableButton;
	}
	
	private Button pauseButton()	{
		Button pauseButton = new Button("Pause");
    	pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return pauseButton;
    }
	
	private Button stepButton()	{
		Button stepButton = new Button("Step");
    	stepButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return stepButton;
    }
	
	private Button resetButton()	{
		Button resetButton = new Button("Reset");
    	resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return resetButton;
    }
	
	private Button undoButton()	{
		Button undoButton = new Button("Undo");
    	undoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return undoButton;
    }
	
	private Button speedUpButton()	{
		Button speedUpButton = new Button("Speed Up");
    	speedUpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return speedUpButton;
    }
	
	private Button slowDownButton()	{
		Button slowDownButton = new Button("SlowDown");
    	slowDownButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
    	return slowDownButton;
    }
	
	private Button colorButton() {
		Button colorButton = new Button("Color Indices");
		colorButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				new ColorPalettes();
			}
		});
		return colorButton;
	}
	
	public void setLanguage(String lang){
		myLanguage = lang;
	}
	
	public String getLanguage(){
		return myLanguage;
	}
	
}
