/**
 * @author Maya Messinger
 * @author Jennifer Chin
 * Pop-up box that displays all of the commands possible, and based on language, how to input them
 */

package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;

public class HelpBox extends Group {
	private Scene helpInternal;
	private Group myGroup;
	private VBox helpVB;

	private static final String SYNTAX = "Syntax";
	private static final String SUBTITLE = "subtitle";

	/**
	 * constructor for box
	 * @param language  determines language that commands should be displayed in
	 */
	public HelpBox(String language)    {
		myGroup = help(language);
		int helpWidth = 600;
		int helpHeight = 800;
		helpInternal = new Scene(myGroup, helpWidth, helpHeight);
		helpInternal.getStylesheets().add(getClass().getResource("SlogoMain.css").toString());
		scroll();
		Stage helpBox = new Stage();
		helpBox.setScene(helpInternal);
		helpBox.setTitle(Resources.getString("HelpTitle"));
		helpBox.show();
	}

	private void scroll(){
		ScrollBar sc = new ScrollBar();
		sc.setLayoutX(helpInternal.getWidth()-sc.getWidth());
		sc.setMin(0);
		int prefHeight = 800;
		sc.setPrefHeight(prefHeight);
		sc.setOrientation(Orientation.VERTICAL);
		myGroup.getChildren().add(sc);

		int scrollScale = -5;
		sc.valueProperty().addListener((ov, old_val, new_val) -> helpVB.setLayoutY(scrollScale * new_val.doubleValue()));
	}

	private Group help(String language){
		int vbSpacing = 5;
		helpVB = new VBox(vbSpacing);
		helpVB.setPadding(new Insets(Resources.getInt(Visualizer.inset), Resources.getInt(Visualizer.inset), Resources.getInt(Visualizer.inset), Resources.getInt(Visualizer.inset)));

		Text title = new Text(Resources.getString("HelpTitle"));
		title.getStyleClass().add("title2");
		helpVB.getChildren().add(title);

		Text basic = new Text("Basic Syntax");
		basic.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(basic);

		Text basicBody = new Text();
		basicBody.setText("Comment = " + ResourcesLanguages.getString(SYNTAX, "Comment")
		+ "\nConstant = " + ResourcesLanguages.getString(SYNTAX, "Constant")
		+ "\nVariable = " + ResourcesLanguages.getString(SYNTAX, "Variable")
		+ "\nCommand = " + ResourcesLanguages.getString(SYNTAX, "Command")
		+ "\nListStart = " + ResourcesLanguages.getString(SYNTAX, "ListStart")
		+ "\nListEnd = " + ResourcesLanguages.getString(SYNTAX, "ListEnd")
		+ "\nGroupStart = " + ResourcesLanguages.getString(SYNTAX, "GroupStart")
		+ "\nGroupEnd = " + ResourcesLanguages.getString(SYNTAX, "GroupEnd")
		+ "\nWhitespace = " + ResourcesLanguages.getString(SYNTAX, "Whitespace")
		+ "\nNewline = " + ResourcesLanguages.getString(SYNTAX, "Newline"));
		basicBody.getStyleClass().add("body");
		helpVB.getChildren().add(basicBody);

		Text turtle = new Text("Turtle Commands");
		turtle.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(turtle);

		Text turtleBody = new Text();
		turtleBody.setText("Forward = " + ResourcesLanguages.getString(language, "Forward")
		+ "\nBackward = " + ResourcesLanguages.getString(language, "Backward")
		+ "\nLeft = " + ResourcesLanguages.getString(language, "Left")
		+ "\nRight = " + ResourcesLanguages.getString(language, "Right")
		+ "\nSetHeading = " + ResourcesLanguages.getString(language, "SetHeading")
		+ "\nSetTowards = " + ResourcesLanguages.getString(language, "SetTowards")
		+ "\nSetPosition = " + ResourcesLanguages.getString(language, "SetPosition")
		+ "\nPenDown = " + ResourcesLanguages.getString(language, "PenDown")
		+ "\nPenUp = " + ResourcesLanguages.getString(language, "PenUp")
		+ "\nShowTurtle = " + ResourcesLanguages.getString(language, "ShowTurtle")
		+ "\nHideTurtle = " + ResourcesLanguages.getString(language, "HideTurtle")
		+ "\nHome = " + ResourcesLanguages.getString(language, "Home")
		+ "\nClearScreen = " + ResourcesLanguages.getString(language, "ClearScreen"));
		turtleBody.getStyleClass().add("body");
		helpVB.getChildren().add(turtleBody);

		Text turtleq = new Text("Turtle Queries");
		turtleq.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(turtleq);

		Text turtleqBody = new Text();
		turtleqBody.setText("XCoordinate = " + ResourcesLanguages.getString(language, "XCoordinate")
		+ "\nYCoordinate = " + ResourcesLanguages.getString(language, "YCoordinate")
		+ "\nHeading = " + ResourcesLanguages.getString(language, "Heading")
		+ "\nIsPenDown = " + ResourcesLanguages.getString(language, "IsPenDown")
		+ "\nIsShowing = " + ResourcesLanguages.getString(language, "IsShowing"));
		turtleqBody.getStyleClass().add("body");
		helpVB.getChildren().add(turtleqBody);

		Text math = new Text("Math Operations");
		math.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(math);

		Text mathBody = new Text();
		mathBody.setText("Sum = " + ResourcesLanguages.getString(language, "Sum")
		+ "\nDifference = " + ResourcesLanguages.getString(language, "Difference")
		+ "\nProduct = " + ResourcesLanguages.getString(language, "IsShowing")
		+ "\nQuotient = " + ResourcesLanguages.getString(language, "Quotient")
		+ "\nRemainder = " + ResourcesLanguages.getString(language, "Remainder")
		+ "\nMinus = " + ResourcesLanguages.getString(language, "Minus")
		+ "\nRandom = " + ResourcesLanguages.getString(language, "Random")
		+ "\nSine = " + ResourcesLanguages.getString(language, "Sine")
		+ "\nCosine = " + ResourcesLanguages.getString(language, "Cosine")
		+ "\nTangent = " + ResourcesLanguages.getString(language, "Tangent")
		+ "\nArcTangent = " + ResourcesLanguages.getString(language, "ArcTangent")
		+ "\nNaturalLog = " + ResourcesLanguages.getString(language, "NaturalLog")
		+ "\nPower = " + ResourcesLanguages.getString(language, "Power")
		+ "\nPi = " + ResourcesLanguages.getString(language, "Pi"));
		mathBody.getStyleClass().add("body");
		helpVB.getChildren().add(mathBody);

		Text bool = new Text("Boolean Operations");
		bool.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(bool);

		Text boolBody = new Text();
		boolBody.setText("LessThan = " + ResourcesLanguages.getString(language, "LessThan")
		+ "\nGreaterThan = " + ResourcesLanguages.getString(language, "GreaterThan")
		+ "\nEqual = " + ResourcesLanguages.getString(language, "Equal")
		+ "\nNotEqual = " + ResourcesLanguages.getString(language, "NotEqual")
		+ "\nAnd = " + ResourcesLanguages.getString(language, "And")
		+ "\nOr = " + ResourcesLanguages.getString(language, "Or")
		+ "\nNot = " + ResourcesLanguages.getString(language, "Not"));
		boolBody.getStyleClass().add("body");
		helpVB.getChildren().add(boolBody);

		Text var = new Text("Variables, Control Structures, and User-Defined Commands");
		var.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(var);

		Text varBody = new Text();
		varBody.setText("MakeVariable = " + ResourcesLanguages.getString(language, "MakeVariable")
		+ "\nRepeat = " + ResourcesLanguages.getString(language, "Repeat")
		+ "\nDoTimes = " + ResourcesLanguages.getString(language, "DoTimes")
		+ "\nFor = " + ResourcesLanguages.getString(language, "For")
		+ "\nIf = " + ResourcesLanguages.getString(language, "If")
		+ "\nIfElse = " + ResourcesLanguages.getString(language, "IfElse")
		+ "\nMakeUserInstruction = " + ResourcesLanguages.getString(language, "MakeUserInstruction"));
		varBody.getStyleClass().add("body");
		helpVB.getChildren().add(varBody);

		Text display = new Text("Display Commands");
		display.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(display);

		Text displayBody = new Text();
		displayBody.setText("SetBackground = " + ResourcesLanguages.getString(language, "SetBackground")
		+ "\nSetPenColor = " + ResourcesLanguages.getString(language, "SetPenColor")
		+ "\nSetPenSize = " + ResourcesLanguages.getString(language, "SetPenSize")
		+ "\nSetShape = " + ResourcesLanguages.getString(language, "SetShape")
		+ "\nSetPalette = " + ResourcesLanguages.getString(language, "SetPalette")
		+ "\nGetPenColor = " + ResourcesLanguages.getString(language, "GetPenColor")
		+ "\nGetShape = " + ResourcesLanguages.getString(language, "GetShape")
		+ "\nStamp = " + ResourcesLanguages.getString(language, "Stamp")
		+ "\nClearStamps = " + ResourcesLanguages.getString(language, "ClearStamps"));
		displayBody.getStyleClass().add("body");
		helpVB.getChildren().add(displayBody);

		Text mult = new Text("Multiple Turtle Commands");
		mult.getStyleClass().add(SUBTITLE);
		helpVB.getChildren().add(mult);

		Text multBody = new Text();
		multBody.setText("ID = " + ResourcesLanguages.getString(language, "ID")
		+ "\nTurtles = " + ResourcesLanguages.getString(language, "Turtles")
		+ "\nTell = " + ResourcesLanguages.getString(language, "Tell")
		+ "\nAsk = " + ResourcesLanguages.getString(language, "Ask")
		+ "\nAskWith = " + ResourcesLanguages.getString(language, "AskWith"));
		multBody.getStyleClass().add("body");
		helpVB.getChildren().add(multBody);

		this.getChildren().add(helpVB);

		return this;
	}
}