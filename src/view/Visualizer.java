/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Main application that runs the whole program, and displays all parts of the GUI
 */

package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import commands.Result;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	private Map<Drawable, ArrayList<String>> drawables = new HashMap<>();
	private ChangeListener myChangeListener;
	private String language = "English";
	
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
		myCanvasObjects = myCanvas.updateCanvas(new ArrayList<Drawable>(drawables.keySet()));
		root.setCenter(myCanvasObjects);

		if (!mySideBar.language.equals(language))	{
			language = mySideBar.language;
			myConsole.language = language;
			myChangeListener.changeLanguage(language);
		}
	}
	
	private Scene initScreen(){
		root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		
		myCanvas = new Canvas(new ArrayList<Drawable>(drawables.keySet()));
		myCanvasObjects = myCanvas.initCanvas(); 
		myCanvasObjects.getStyleClass().addAll("pane", "border");
		root.setCenter(myCanvasObjects);
		
		mySideBar = new SideBar(myCanvasObjects, new ArrayList<Drawable>(drawables.keySet()), myCanvas);
		((SideBar)mySideBar).language = language;
		root.setRight(((SideBar)mySideBar).initSideBar());

		myConsole = new Console();
		((Console)myConsole).language = language;
		root.setBottom(((Console)myConsole));

		myToolbar = new Toolbar();
		root.setTop(myToolbar.initToolbar());

		myCanvas.myVBox = mySideBar;
		((Console)myConsole).myChangeListener = myChangeListener;
		((SideBar)mySideBar).myTextInput = myConsole;
		((Console)myConsole).myVBox = mySideBar;
		
		return scene;
	}

	/**
	 * Called by Engine, takes in a drawable made in Engine and sends to canvas to draw. This is another middle man to separate model and view
	 * @param turtle	object to be drawn on canvas
	 */
	public void addDrawable(Drawable turtle)	{
		//myCanvas.addDrawable(turtle);
		List<String> properties = new ArrayList<String>();
		properties.add(Integer.toString(drawables.size()));
		properties.add("true");
		properties.add("turtle.png");
		properties.add("up");
		properties.add("black");
		properties.add("2");
		properties.add("100");
		properties.add("400");
		properties.add("40");

		drawables.put(turtle, (ArrayList)properties);
	}

	/**
	 * Controller functionality that passes visual elements the necessary info to update them
	 * @param result	Result ojbect passed back from Engine that gives a double return value
	 */
	public void runCommand(Result result)	{
		((Console)myConsole).printResult(Double.toString(result.getRes1()));
		myCanvas.updateCanvas(result);
	}

}