package view;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.keys.Resources;
import slogo_team07.Console;
import slogo_team07.Turtle;

public class SlogoMain extends Application{
	
	public static final int FRAMES_PER_SECOND = 5;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Canvas myCanvas;
	private GridPane myCanvasObjects;
	private Console myConsole;
	private SideBar mySideBar;
	
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
		
	}
	
	private Scene initScreen(){
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		
		ArrayList<Turtle> test = new ArrayList<Turtle>();
		//may not need canvas, can just set center as group
		myCanvas = new Canvas();
		myCanvasObjects = myCanvas.initCanvas(test); //need turtles to display
		root.setCenter(myCanvasObjects);
		
		myConsole = new Console();
		root.setBottom(myConsole);
		
		mySideBar = new SideBar();
		root.setRight(mySideBar.initSideBar());
		
		Text title = new Text(Resources.getString("Title"));
		root.setTop(title);
		
		return scene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
