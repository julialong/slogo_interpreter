package view;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.keys.Resources;
import slogo_team07.Drawable;
import slogo_team07.Turtle;
import commands.Result;

public class SlogoMain extends Application{
	
	public static final int FRAMES_PER_SECOND = 5;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Canvas myCanvas;
	private Pane myCanvasObjects;
	private SideBar mySideBar;
	private Console myConsole;
	private Toolbar myToolbar;
	private String language = "English";
	
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
		if (!mySideBar.language.equals(language))	{
			language = mySideBar.language;
			myConsole.language = language;
			// ChangeListener .changeLanguage(language);
		}
	}
	
	private Scene initScreen(){
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, Resources.getInt("ScreenWidth"), Resources.getInt("ScreenHeight"), Resources.getColor("BackgroundColor"));
		scene.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		
		//hardcoded, need to get from backend
		ArrayList<Drawable> test = new ArrayList<Drawable>();
		Turtle testTurt = new Turtle();
		test.add(testTurt);
		
		myCanvas = new Canvas(test);
		myCanvasObjects = myCanvas.initCanvas(); 
		myCanvasObjects.getStyleClass().addAll("pane", "border");
		root.setCenter(myCanvasObjects);
		
		mySideBar = new SideBar(myCanvasObjects, test);
		((SideBar)mySideBar).language = language;
		root.setRight(((SideBar)mySideBar).initSideBar());

		myConsole = new Console();
		root.setBottom(((Console)myConsole));

		((SideBar)mySideBar).myConsole = myConsole;
		((Console)myConsole).myVBox = mySideBar;
		
		myToolbar = new Toolbar();
		root.setTop(myToolbar.initToolbar());
		
		return scene;
	}

	public void updateView(Result result)	{
		if (result.getRes1() == Double.MAX_VALUE)	{
			new Alert();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}