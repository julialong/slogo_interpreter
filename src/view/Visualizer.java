/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Main application that runs the whole program, and displays all parts of the GUI
 */

package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import commands.Result;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.keys.Resources;
import slogo_team07.ChangeListener;
import slogo_team07.Drawable;

public class Visualizer {
	
	public static final int FRAMES_PER_SECOND = 5;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Canvas myCanvas;
	private Pane myCanvasObjects;
	private SideBar mySideBar;
	private Console myConsole;
	private Toolbar myToolbar;
	private BorderPane root;
	private Map<Drawable, List<String>> drawables = new HashMap<>();
	private ChangeListener myChangeListener;
	private String language = "English";

	protected ObservableList<IndCol> bgColors = FXCollections.observableArrayList(new IndCol(0, Color.WHITE), new IndCol(1, Color.RED), new IndCol(2, Color.ORANGE), new IndCol(3, Color.YELLOW), new IndCol(4, Color.GREEN),
		new IndCol(5, Color.BLUE), new IndCol(6, Color.PURPLE), new IndCol(7, Color.PINK));

	protected ObservableList<IndCol> penColors = FXCollections.observableArrayList( new IndCol(0, Color.BLACK), new IndCol(1, Color.WHITE), new IndCol(2, Color.RED), new IndCol(3, Color.ORANGE), new IndCol(4, Color.YELLOW),
		new IndCol(5, Color.GREEN), new IndCol(6, Color.BLUE), new IndCol(7, Color.PURPLE), new IndCol(8, Color.PINK));
	
	public Visualizer(Stage stage, ChangeListener change_listener) {
		myChangeListener = change_listener;
		
		Scene myScene = initScreen();
		stage.setScene(myScene);
		stage.setTitle(Resources.getString("Title"));
		stage.show();
		setupAnimation();
	}
	
	private void setupAnimation(){
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double cycles){
		myCanvasObjects = myCanvas.updateCanvas(drawables);
		root.setCenter(myCanvasObjects);

		if (!myToolbar.getLanguage().equals(language))	{
			language = myToolbar.getLanguage();
			myConsole.language = language;
			myChangeListener.changeLanguage(language);
		}
	}
	
	private Scene initScreen(){
		root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		
		myCanvas = new Canvas(drawables);
		myCanvasObjects = myCanvas.initCanvas(); 
		myCanvasObjects.getStyleClass().addAll("pane", "border");
		root.setCenter(myCanvasObjects);
		
		mySideBar = new SideBar(myCanvasObjects, this, drawables, myCanvas);
		root.setRight(mySideBar.initSideBar());

		myConsole = new Console();
		myConsole.language = language;
		root.setBottom(myConsole);

		myToolbar = new Toolbar(this, myCanvasObjects);
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
	 * @param turtle	object to be drawn on canvas
	 */
	public void addDrawable(Drawable turtle)	{
		//myCanvas.addDrawable(turtle);
		List<String> properties = new ArrayList<String>();
		properties.add(String.valueOf((int)turtle.getId())); // id
		properties.add(String.valueOf(true)); // add active
		properties.add("Turtle"); // add image (ex. Turtle, not extension)
		properties.add(String.valueOf(turtle.getIsDown()));// pen (up or down)
		properties.add(String.valueOf((int)turtle.getPenColor()));// pen color
		properties.add(Double.toString(turtle.getPenWidth())); // pen width

		drawables.put(turtle, properties);
	}

	/**
	 * Controller functionality that passes visual elements the necessary info to update them
	 * @param result	Result ojbect passed back from Engine that gives a double return value
	 */
	public void runCommand(Result result)	{
		myConsole.printResult(Double.toString(result.getRes1()));
		myCanvas.updateCanvas(result);
	}

	/**
	 * Called by SetBackground command, sets canvas pane background to one based on index in ColorPalette
	 * @param index	index within possible colors to set background to
	 */
	public void setBackground(int index)	{
		
	}

	/**
	 * Called by SetPalette command, changes the color value in color palete at index to new RGB color
	 * @param index	index of color to set in palette
	 * @param r		red amount of new color
	 * @param g		green amount of new color
	 * @paran b		blue amount of new color
	 */
	public double setPalette(int index, Double r, Double g, Double b)	{
		bgColors.set(index, new IndCol(index, Color.rgb(r.intValue(), g.intValue(), b.intValue())));

		return index;
	}

	/**
	 * Class that has properties that TableView can read in order to import into table
	 * Only public so PropertyValueFactory can get its properties
	 */
	public class IndCol	{
		private SimpleIntegerProperty ind;
		private SimpleObjectProperty color;

		private IndCol(int anInd, Color aColor)	{
			ind = new SimpleIntegerProperty(anInd);
			Shape colorBox = new Rectangle(15, 15, aColor);
			color = new SimpleObjectProperty(colorBox);
		}

		/**
		 * Returns the name of a variable, as a property
		 */
    	public IntegerProperty indProperty() {
	        return ind;
	    }

	    /**
	     * Returns the color of a variable, as a property
	     */
	    public ObjectProperty colorProperty()	{
	    	return color;
	    }
	}
}