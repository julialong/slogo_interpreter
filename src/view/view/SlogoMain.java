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
import view.Console;
import slogo_team07.Drawable;
import slogo_team07.Turtle;

public class SlogoMain extends Application{
	
	public static final int FRAMES_PER_SECOND = 5;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Canvas myCanvas;
	private Pane myCanvasObjects;
	private Console myConsole;
	private SideBar mySideBar;
	private Toolbar myToolbar;
	private BorderPane root;
	private ArrayList<Drawable> myTurtles = new ArrayList<Drawable>();
	
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
	}
	
	private Scene initScreen(){
		root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		
		//hardcoded, need to get from backend
		Turtle testTurt = new Turtle(0, 0);
		myTurtles.add(testTurt);
		
		myCanvas = new Canvas(myTurtles);
		myCanvasObjects = myCanvas.initCanvas(); 
		myCanvasObjects.getStyleClass().addAll("pane", "border");
		root.setCenter(myCanvasObjects);
		
		myConsole = new Console();
		root.setBottom(myConsole);
		
		mySideBar = new SideBar(myCanvasObjects, myTurtles);
		root.setRight(mySideBar.initSideBar());
		
		myToolbar = new Toolbar();
		root.setTop(myToolbar.initToolbar());
		
		return scene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}