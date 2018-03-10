/**
 * @author Jennifer Chin
 * @author Julia Long
 * @author Maya Messinger
 * Started 27 Feb 18
 * Upper navigation bar that holds title of program and all buttons that affect global variables (that exist in whole program)
 */

package view;

import file_managers.FileReader;
import file_managers.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import slogo_team07.Drawable;
import slogo_team07.Turtle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Toolbar extends AnchorPane {
	private Visualizer myVis;
	private AnchorPane myPane;
	private Pane myCanvasObjects;
	private FileWriter myFileWriter;
	private FileReader myFileReader;
	protected static ObservableList<String> langsSupported = FXCollections.observableArrayList("Chinese", "English",
			"French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
	private String myLanguage;

	/**
	 * Toolbar constructor. Takes in a Visualizer in order to create another visualizer with the same change
	 * listener. Takes in a canvas for background color button. Takes in a fileWriter and fileReader to save and 
	 * load new files. 
	 * @param v
	 * @param canvas
	 * @param fileWriter
	 * @param fileReader
	 * @param stage
	 */

	public Toolbar(Visualizer v, Pane canvas, FileWriter fileWriter, FileReader fileReader){
		myVis = v;
		myCanvasObjects = canvas;
		myFileWriter = fileWriter;
		myFileReader = fileReader;
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

	//need to send tell command to create initial turtle on new window
	private Button windowButton()	{
		Button windowButton = new Button("New Window");
		windowButton.setOnAction(e -> {
			new Visualizer(new Stage(), myVis.getChangeListener());
		});
		return windowButton;
	}

	/**
	 * Creates a button that, when pressed, asks for a file name to save to and then saves status of current
	 * user defined commands to that file
	 * @return new save Button
	 */
	private Button saveButton()	{
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> {
			promptForFilename();
		});
		return saveButton;
	}

	/**
	 * Creates a button that, when pressed, opens a filechooser and loads file to console
	 * @return new load Button
	 */
	private Button loadButton()	{
		Button loadButton = new Button("Load");
		loadButton.setOnAction(e -> {
			try {
				myFileReader.readFile(openFileChooser());
			}
			catch (Exception exception) {
				// TODO: HANDLE EXCEPTION
			}
		});
		return loadButton;
	}

	private VBox initButtons(){
		HBox myHBox = new HBox(Resources.getInt(Visualizer.inset));
		Button helpButton = helpButton();
		myHBox.getChildren().add(helpButton);
		myHBox.getChildren().add(colorMenu());
		myHBox.getChildren().add(langMenu(helpButton));
		myHBox.getChildren().add(windowButton());
		myHBox.getChildren().add(saveButton());
		myHBox.getChildren().add(loadButton());

		HBox buttons = new HBox(Resources.getInt(Visualizer.inset));
		buttons.getChildren().add(pauseButton());
		buttons.getChildren().add(stepButton());
		buttons.getChildren().add(resetButton());
		buttons.getChildren().add(undoButton());
		buttons.getChildren().add(speedUpButton());
		buttons.getChildren().add(slowDownButton());
		buttons.getChildren().add(addDrawableButton());
		buttons.getChildren().add(colorButton());

		VBox myVBox = new VBox(Resources.getInt(Visualizer.inset));
		myVBox.setPadding(new Insets(Resources.getInt(Visualizer.inset)));
		myVBox.getChildren().add(myHBox);
		myVBox.getChildren().add(buttons);

		return myVBox;
	}

	//NOT SURE HOW TO LINK W BACKEND
	private Button addDrawableButton()	{
		Button addDrawableButton = new Button("Add Turtle");
		addDrawableButton.setOnAction(e -> {
			int id = myVis.drawables.size();
			List<String> ids = new ArrayList<String>();
			for (Map.Entry<Drawable, List<String>> entry: myVis.drawables.entrySet()){
				ids.add(myVis.drawables.get(entry.getKey()).get(0));
			}
			while (ids.contains(String.valueOf(id))){
				id++;
			}
			myVis.addDrawable(new Turtle(String.valueOf(id)));
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

	/**
	 * Returns the String name of the language that this program is accepting commands and displaying help in
	 * @return String
	 */
	public String getLanguage(){
		return myLanguage;
	}

	/**
	 * Opens a file chooser window for the user to select their file
	 */
	private File openFileChooser() {
		FileChooser fc = new FileChooser();
		fc.setTitle(Resources.getString("ChooserTitle"));
		return fc.showOpenDialog(new Stage());
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
			// TODO: HANDLE EXCEPTION
		}
	}

}
