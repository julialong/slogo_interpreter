/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Main application that runs the whole program, and displays all parts of the GUI
 */

package view;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.keys.Resources;
import slogo_team07.ChangeListener;
import slogo_team07.Drawable;
import slogo_team07.Updatable;
import slogo_team07.Engine;
import slogo_team07.Turtle;
import commands.Result;

public class Visualizer extends Application{
	
	public static final int FRAMES_PER_SECOND = 5;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Canvas myCanvas;
	private Pane myCanvasObjects;
	private SideBar mySideBar;
	private Console myConsole;
	private Toolbar myToolbar;
	private BorderPane root;
	private ArrayList<Drawable> myTurtles = new ArrayList<Drawable>();
	private ChangeListener myEngine;
	private String language = "English";
	
	/**
	 * Default application start method that begins to run program
	 */
	public void start(Stage primaryStage) throws Exception {
		Stage myStage = primaryStage;
		Scene myScene = initScreen();
		myStage.setScene(myScene);
		myStage.setTitle(Resources.getString("Title"));
		myStage.show();
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
		myCanvasObjects = myCanvas.updateCanvas();
		root.setCenter(myCanvasObjects);

		if (!mySideBar.language.equals(language))	{
			language = mySideBar.language;
			myConsole.language = language;
			myEngine.changeLanguage(language);
		}
	}
	
	private Scene initScreen(){
		root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());

		//hardcoded, need to get from backend
		ArrayList<Drawable> test = new ArrayList<Drawable>();
		Turtle testTurt = new Turtle();
		test.add(testTurt);
		
		myCanvas = new Canvas(myTurtles);
		myCanvasObjects = myCanvas.initCanvas(); 
		myCanvasObjects.getStyleClass().addAll("pane", "border");
		root.setCenter(myCanvasObjects);
		
		mySideBar = new SideBar(myCanvasObjects, test);
		((SideBar)mySideBar).language = language;
		root.setRight(((SideBar)mySideBar).initSideBar());

		myConsole = new Console();
		((Console)myConsole).language = language;
		root.setBottom(((Console)myConsole));

		myToolbar = new Toolbar();
		root.setTop(myToolbar.initToolbar());

		myEngine = new Engine(this);

		myCanvas.myVBox = mySideBar;
		((Console)myConsole).myCL = myEngine;
		((SideBar)mySideBar).myTextInput = myConsole;
		((Console)myConsole).myVBox = mySideBar;
		
		return scene;
	}

	/**
	 * Called by Engine, takes in a drawable made in Engine and sends to canvas to draw. This is another middle man to separate model and view
	 * @param turtle	object to be drawn on canvas
	 */
	public void addDrawable(Drawable turtle)	{
		myCanvas.addDrawable(turtle);
	}

	/**
	 * Controller functionality that passes visual elements the necessary info to update them
	 * @param result	Result ojbect passed back from Engine that gives a double return value
	 */
	public void runCommand(Result result)	{
		((Console)myConsole).printResult(Double.toString(result.getRes1()));
		myCanvas.updateCanvas(result);
	}
	
	/**
	 * Runs the program
	 */
	public static void main(String[] args) {
		launch(args);
	}

}