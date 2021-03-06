/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Main application that runs the whole program, and displays all parts of the GUI
 */

package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commands.ErrorResult;
import commands.Result;
import file_managers.FileReader;
import file_managers.FileWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.keys.Resources;
import slogo_team07.ChangeListener;
import slogo_team07.Drawable;

public class Visualizer {

	public static final int FRAMES_PER_SECOND = 5;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String inset = "Inset";
	public static final ObservableList<String> possBackgroundColors = FXCollections.observableArrayList("White", "Red",
			"Orange", "Yellow", "Green", "Blue", "Purple", "Pink");
	public static final ObservableList<String> possPenColors = FXCollections.observableArrayList("Black", "White",
			"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink");
	public static final ObservableList<String> possIVImages = FXCollections.observableArrayList("Turtle", "Bird",
			"Butterfly", "Cat", "Dog", "Fish", "Octopus");

	private Canvas myCanvas;
	private Pane myCanvasObjects;
	private SideBar mySideBar;
	private Console myConsole;
	private Toolbar myToolbar;
	private BorderPane root;
	protected Map<Drawable, List<String>> drawables = new HashMap<>();
	private ChangeListener myChangeListener;
	private String language = "English";

	protected ObservableList<IndCol> bgColors = FXCollections.observableArrayList();
	protected ObservableList<IndCol> penColors = FXCollections.observableArrayList();
	protected ObservableList<IndImg> ivImages = FXCollections.observableArrayList();

	/**
	 * @param stage stage that this Visulizer is displaying in
	 * @param change_listener ChangeListener that links to backend and operates on commands
	 */
	public Visualizer(Stage stage, ChangeListener change_listener) {
		myChangeListener = change_listener;
		Scene myScene = initScreen();
		stage.setScene(myScene);
		stage.setTitle(Resources.getString("Title"));
		stage.show();
		setupAnimation();

		for (int i = 0; i < possBackgroundColors.size(); i++) {
			bgColors.add(new IndCol(i, Color.valueOf(possBackgroundColors.get(i))));
		}

		for (int i = 0; i < possPenColors.size(); i++) {
			penColors.add(new IndCol(i, Color.valueOf(possPenColors.get(i))));
		}

		for (int i = 0; i < possIVImages.size(); i++) {
			ivImages.add(new IndImg(i, possIVImages.get(i)));
		}
	}

	private void setupAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step(double cycles) {
		myCanvasObjects = myCanvas.updateCanvas(drawables);
		root.setCenter(myCanvasObjects);
		// dragAndDrop();

		if (!myToolbar.getLanguage().equals(language)) {
			language = myToolbar.getLanguage();
			myConsole.language = language;
			myChangeListener.changeLanguage(language);
		}
	}

	private Scene initScreen() {
		root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"),
				Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());

		myCanvas = new Canvas(drawables);
		myCanvasObjects = myCanvas.initCanvas();
		myCanvasObjects.getStyleClass().addAll("pane", "border");
		root.setCenter(myCanvasObjects);

		mySideBar = new SideBar(myCanvasObjects, this, drawables);
		root.setRight(mySideBar.initSideBar());

		myConsole = new Console();
		myConsole.language = language;
		root.setBottom(myConsole);

		FileWriter myFileWriter = new FileWriter(mySideBar);
		FileReader myFileReader = new FileReader(myConsole);

		myToolbar = new Toolbar(this, myCanvasObjects, myFileWriter, myFileReader);
		myToolbar.setLanguage(language);
		root.setTop(myToolbar.initToolbar());

		myCanvas.myVBox = mySideBar;
		myConsole.myChangeListener = myChangeListener;
		mySideBar.myTextInput = myConsole;
		myConsole.myVBox = mySideBar;

		return scene;
	}

	/**
	 * Called by Engine, takes in a drawable made in Engine and sends to canvas to draw. This is another
	 * middle man to separate model and view
	 * 
	 * @param turtle object to be drawn on canvas
	 */
	public void addDrawable(Drawable turtle) {
		// myCanvas.addDrawable(turtle);
		List<String> properties = new ArrayList<>();
		properties.add(String.valueOf((int) turtle.getId())); // id
		properties.add(String.valueOf(true)); // add active
		properties.add("Turtle"); // add image (ex. Turtle, not extension)
		properties.add(String.valueOf(turtle.getIsDown()));// pen (up or down)
		properties.add(String.valueOf((int) turtle.getPenColor()));// pen color
		properties.add(Double.toString(turtle.getPenWidth())); // pen width

		drawables.put(turtle, properties);
	}

	/**
	 * Default method - Controller functionality that passes visual elements the necessary info to
	 * update them
	 * 
	 * @param result Result ojbect passed back from Engine that gives a double return value
	 */
	public void runCommand(Result result) {
		runCommand(result, false);
	}

	/**
	 * Controller functionality that passes visual elements the necessary info to update them
	 * 
	 * @param result Result ojbect passed back from Engine that gives a double return value
	 * @param isLast Whether command is last in serires - only want to actually print last command
	 *        result (recursive commands get long)
	 */
	public void runCommand(Result result, boolean isLast) {
		if (result.getClass() == ErrorResult.class) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Improper command");
			alert.setContentText(result.toString());
			alert.show();
		} else {
			myCanvas.updateCanvas(result);

			if (isLast) {
				myConsole.printResult(result.toString());
			}

		}
	}

	protected ChangeListener getChangeListener() {
		return myChangeListener;
	}

	/**
	 * Called by a TO command, in order to send a user-defined function to the table of user-defined
	 * functions
	 * 
	 * @param functionText command from backend that will be displayed on button
	 */
	public void addNewFunc(String functionText) {
		mySideBar.addUDIButton(functionText);
	}

	/**
	 * Called by a MAKE or SET command, in order to send a user-defined variable to the table of
	 * variables
	 * 
	 * @param variable name of variable made
	 * @param value value of variable
	 */
	public void addNewVar(String variable, String value) {
		mySideBar.addUDVar(variable, Double.parseDouble(value));
	}

	/**
	 * Called by SetBackground command, sets canvas pane background to one based on index in
	 * ColorPalette
	 * 
	 * @param index index within possible colors to set background to
	 */
	public void setBackground(int index) {
		myCanvasObjects.getStyleClass().removeAll("pane", "red-back", "orange-back", "yellow-back", "green-back",
				"blue-back", "purple-back", "pink-back");
		myCanvasObjects.setStyle("-fx-background-color: #"
				+ ((Shape) (bgColors.get(index).colorProperty().getValue())).getFill().toString().substring(2, 8)
				+ ";");
	}

	/**
	 * Called by SetPalette command, changes the color value in color palete at index to new RGB color
	 * 
	 * @param index index of color to set in palette
	 * @param r red amount of new color
	 * @param g green amount of new color
	 * @paran b blue amount of new color
	 */
	public double setPalette(int index, Double r, Double g, Double b) {
		bgColors.set(index, new IndCol(index, Color.rgb(r.intValue(), g.intValue(), b.intValue())));
		return index;
	}

	/**
	 * Class that has properties that TableView can read in order to import into table Only public so
	 * PropertyValueFactory can get its properties
	 */
	public class IndCol {
		private SimpleIntegerProperty ind;
		private SimpleObjectProperty color;

		private IndCol(int anInd, Color aColor) {
			ind = new SimpleIntegerProperty(anInd);
			int boxDim = 15;
			Shape colorBox = new Rectangle(boxDim, boxDim, aColor);
			color = new SimpleObjectProperty(colorBox);
		}

		/**
		 * Returns the index of a variable, as a property
		 */
		public IntegerProperty indProperty() {
			return ind;
		}

		/**
		 * Returns the color of a variable, as a property
		 */
		public ObjectProperty colorProperty() {
			return color;
		}
	}

	/**
	 * Class that has properties that TableView can read in order to import into table Only public so
	 * PropertyValueFactory can get its properties
	 */
	public class IndImg {
		private SimpleIntegerProperty ind;
		private SimpleObjectProperty image;

		private IndImg(int anInd, String imgString) {
			ind = new SimpleIntegerProperty(anInd);
			int imgDim = 15;
			ImageView icon = new ImageView(new Image(Resources.getString(imgString)));
			icon.setFitHeight(imgDim);
			icon.setFitWidth(imgDim);
			image = new SimpleObjectProperty(icon);
		}

		/**
		 * Returns the index of a variable, as a property
		 */
		public IntegerProperty indProperty() {
			return ind;
		}

		/**
		 * Returns the color of a variable, as a property
		 */
		public ObjectProperty imageProperty() {
			return image;
		}
	}
}