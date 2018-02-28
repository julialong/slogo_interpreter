package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import resources.languages.ResourcesLanguages;

public class HelpBox extends Group {
    public HelpBox(String language)    {
        //this.getChildren().add(helpButton());
		Scene helpInternal = new Scene(help(language), 400, 800);
		Stage helpBox = new Stage();
		helpBox.setScene(helpInternal);
		helpBox.show();
    }

    public Group help(String language)	{
    	Group help = new Group();

    	Text manual = new Text();
    	manual.setText(
    		"Comment = " + ResourcesLanguages.getString("Syntax", "Comment")
			+ "\nConstant = " + ResourcesLanguages.getString("Syntax", "Constant")
			+ "\nVariable = " + ResourcesLanguages.getString("Syntax", "Variable")
			+ "\nCommand = " + ResourcesLanguages.getString("Syntax", "Command")
			+ "\nListStart = " + ResourcesLanguages.getString("Syntax", "ListStart")
			+ "\nListEnd = " + ResourcesLanguages.getString("Syntax", "ListEnd")
			+ "\nGroupStart = " + ResourcesLanguages.getString("Syntax", "GroupStart")
			+ "\nGroupEnd = " + ResourcesLanguages.getString("Syntax", "GroupEnd")
			+ "\nWhitespace = " + ResourcesLanguages.getString("Syntax", "Whitespace")
			+ "\nNewline = " + ResourcesLanguages.getString("Syntax", "Newline")
			+ "\n"
			+ "\nForward = " + ResourcesLanguages.getString(language, "Forward")
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
			+ "\nClearScreen = " + ResourcesLanguages.getString(language, "ClearScreen")
			+ "\n"
			+ "\nXCoordinate = " + ResourcesLanguages.getString(language, "XCoordinate")
			+ "\nYCoordinate = " + ResourcesLanguages.getString(language, "YCoordinate")
			+ "\nHeading = " + ResourcesLanguages.getString(language, "Heading")
			+ "\nIsPenDown = " + ResourcesLanguages.getString(language, "IsPenDown")
			+ "\nIsShowing = " + ResourcesLanguages.getString(language, "IsShowing")
			+ "\n"
			+ "\nSum = " + ResourcesLanguages.getString(language, "Sum")
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
			+ "\nPi = " + ResourcesLanguages.getString(language, "Pi")
			+ "\n"
			+ "\nLessThan = " + ResourcesLanguages.getString(language, "LessThan")
			+ "\nGreaterThan = " + ResourcesLanguages.getString(language, "GreaterThan")
			+ "\nEqual = " + ResourcesLanguages.getString(language, "Equal")
			+ "\nNotEqual = " + ResourcesLanguages.getString(language, "NotEqual")
			+ "\nAnd = " + ResourcesLanguages.getString(language, "And")
			+ "\nOr = " + ResourcesLanguages.getString(language, "Or")
			+ "\nNot = " + ResourcesLanguages.getString(language, "Not")
			+ "\n"
			+ "\nMakeVariable = " + ResourcesLanguages.getString(language, "MakeVariable")
			+ "\nRepeat = " + ResourcesLanguages.getString(language, "Repeat")
			+ "\nDoTimes = " + ResourcesLanguages.getString(language, "DoTimes")
			+ "\nFor = " + ResourcesLanguages.getString(language, "For")
			+ "\nIf = " + ResourcesLanguages.getString(language, "If")
			+ "\nIfElse = " + ResourcesLanguages.getString(language, "IfElse")
			+ "\nMakeUserInstruction = " + ResourcesLanguages.getString(language, "MakeUserInstruction")
			+ "\n"
			+ "\nSetBackground = " + ResourcesLanguages.getString(language, "SetBackground")
			+ "\nSetPenColor = " + ResourcesLanguages.getString(language, "SetPenColor")
			+ "\nSetPenSize = " + ResourcesLanguages.getString(language, "SetPenSize")
			+ "\nSetShape = " + ResourcesLanguages.getString(language, "SetShape")
			+ "\nSetPalette = " + ResourcesLanguages.getString(language, "SetPalette")
			+ "\nGetPenColor = " + ResourcesLanguages.getString(language, "GetPenColor")
			+ "\nGetShape = " + ResourcesLanguages.getString(language, "GetShape")
			+ "\nStamp = " + ResourcesLanguages.getString(language, "Stamp")
			+ "\nClearStamps = " + ResourcesLanguages.getString(language, "ClearStamps")
			+ "\n"
			+ "\nID = " + ResourcesLanguages.getString(language, "ID")
			+ "\nTurtles = " + ResourcesLanguages.getString(language, "Turtles")
			+ "\nTell = " + ResourcesLanguages.getString(language, "Tell")
			+ "\nAsk = " + ResourcesLanguages.getString(language, "Ask")
			+ "\nAskWith = " + ResourcesLanguages.getString(language, "AskWith"));

		help.getChildren().add(manual);

    	return help;
    }
}