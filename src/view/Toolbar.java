package view;

import file_managers.FileReader;
import file_managers.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Toolbar extends AnchorPane {
	private Visualizer myVis;
	private AnchorPane myPane;
	private Pane myCanvasObjects;
	private Stage myStage;
	private FileWriter myFileWriter;
	private FileReader myFileReader;
	private static ObservableList<String> colorList = Canvas.colorList;
	protected static ObservableList<String> langsSupported = FXCollections.observableArrayList("Chinese", "English",
			"French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	private String myLanguage;
	
	public Toolbar(Visualizer v, Pane canvas, FileWriter fileWriter, FileReader fileReader, Stage stage){
		myVis = v;
		myCanvasObjects = canvas;
		myFileWriter = fileWriter;
		myFileReader = fileReader;
		myStage = stage;
	}
	
	protected AnchorPane initToolbar(){
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
    	helpButton.setOnAction(e -> new HelpBox(myLanguage));
    	return helpButton;
    }
	
	private ComboBox colorMenu()	{
		ComboBox colorMenu = new ComboBox(Visualizer.possBackgroundColors);
		colorMenu.setPromptText(Resources.getString("ColorMenu"));
		colorMenu.setOnAction(e -> {
            String tempColor = colorMenu.getSelectionModel().getSelectedItem().toString();
            myCanvasObjects.getStyleClass().removeAll("pane", "red-back", "orange-back", "yellow-back",
                    "green-back", "blue-back", "purple-back", "pink-back");
            myCanvasObjects.getStyleClass().add(Resources.getString(tempColor));
        });
		return colorMenu;
	}
	
	private ComboBox langMenu(Button helpButton)	{
		ComboBox langMenu = new ComboBox<String>(langsSupported);
		langMenu.setPromptText(Resources.getString("LangMenu"));
		langMenu.setOnAction(e -> {
            myLanguage = (String)(langMenu.getValue());
            helpButton.setText(ResourcesLanguages.getString(myLanguage, "Help"));
        });
		return langMenu;
	}
	
	//need to write
	private Button windowButton()	{
		Button windowButton = new Button("New Window");
    	windowButton.setOnAction(e -> {
            // TODO: need to write
        });
    	return windowButton;
    }
	
	//need to sync w parser
	private Button saveButton()	{
		Button saveButton = new Button("Save");
    	saveButton.setOnAction(e -> {
			promptForFilename();
        });
    	return saveButton;
    }
	
	//need to sync w parser
	private Button loadButton()	{
		Button loadButton = new Button("Load");
    	loadButton.setOnAction(e -> {

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
	    addDrawableButton.setOnAction(e -> {
        });
		return addDrawableButton;
	}
	
	private Button pauseButton()	{
		Button pauseButton = new Button("Pause");
    	pauseButton.setOnAction(e -> {

        });
    	return pauseButton;
    }
	
	private Button stepButton()	{
		Button stepButton = new Button("Step");
    	stepButton.setOnAction(e -> {

        });
    	return stepButton;
    }
	
	private Button resetButton()	{
		Button resetButton = new Button("Reset");
    	resetButton.setOnAction(e -> {

        });
    	return resetButton;
    }
	
	private Button undoButton()	{
		Button undoButton = new Button("Undo");
    	undoButton.setOnAction(e -> {

        });
    	return undoButton;
    }
	
	private Button speedUpButton()	{
		Button speedUpButton = new Button("Speed Up");
    	speedUpButton.setOnAction(e -> {

        });
    	return speedUpButton;
    }
	
	private Button slowDownButton()	{
		Button slowDownButton = new Button("SlowDown");
    	slowDownButton.setOnAction(e -> {

        });
    	return slowDownButton;
    }
	
	private Button colorButton() {
		Button colorButton = new Button("Color Indices");
		colorButton.setOnAction(e -> new ColorPalettes(myVis));
		return colorButton;
	}
	
	public void setLanguage(String lang){
		myLanguage = lang;
	}
	
	public String getLanguage(){
		return myLanguage;
	}

	/**
	 * Opens a file chooser window for the user to select their file
	 */
	private void openFileChooser() {
		File configFile = new FileChooser().showOpenDialog(myStage);
		if (configFile != null) {

		}
	}

	/**
	 * Prompts user for the filename of the text file they want to write
	 */
	private void promptForFilename() {
		TextInputDialog prompt = new TextInputDialog("newfile");
		prompt.setTitle("Write to file");
		prompt.setHeaderText("Please enter the name of your file.");
		Optional<String> result = prompt.showAndWait();
		result.ifPresent(this::writeToFile);
	}

	/**
	 * Calls File Writer to write to file
	 */
	private void writeToFile(String filename) {
		try {
			myFileWriter.writeToFile(filename);
		}
		catch (Exception e) {
			//TODO: HANDLE THIS
		}
	}
	
}
