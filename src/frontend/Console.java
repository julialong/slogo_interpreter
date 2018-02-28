/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Handles input of all commands from user in program. Has run, clear, and recall command functionality
 */

//package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;
import slogo_team07.ChangeListener;

public class Console extends AnchorPane implements TextInput {
    private TextArea console;
    private TextArea history;
    private Button runner;
    private Button clearer;
    private List<String> pastCommands;
    protected VBox myVBox;
    protected ChangeListener myCL;
    protected String language;

    private int offsetPad = 0;
    private int width = 700;
    private int cHeight = 100;
    private int hHeight = 80;
    private int bHeight = (cHeight + hHeight + offsetPad + 5*Resources.getInt("Inset"))/3;
    private int bWidth = 80;
    private int commandIndex = -1;   // will track what command is "last" for scrollability

    /**
     * Constructor for Console
     */
    public Console()    {
        initConsole();
    }

    /**
     * Initialized this Console with variables/contents
     */
    public AnchorPane initConsole() {
        console = new TextArea();
        history = new TextArea();
        runner = setRunner();
        clearer = setClearer();
        pastCommands = new ArrayList<>();
        
        VBox myButtons = new VBox(Resources.getInt("Inset"));
        myButtons.setPadding(new Insets(Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset"), Resources.getInt("Inset")));
        myButtons.getChildren().addAll(runner, clearer);
        this.getChildren().add(myButtons);
        addElements();
        this.setTopAnchor(history, 0.0);
        this.setBottomAnchor(console, 0.0);
        this.setRightAnchor(myButtons, 0.0);

        return this;
    }

     /**
     * Sends text from console to anything that calls run(), clears text box, and adds command to history
     */
    @Override
    public String run()    {
        String comm = console.getText();

        pastCommands.add(comm);
        history.appendText("\n" + Integer.toString(pastCommands.size()) + ": " + comm);
        clear();

         myCL.changeInput(comm);
        checkSpecial(comm);

        return comm;
    }
    
    /**
     * Clears text in console
     */
    @Override
    public void clear() {
        console.clear();
    }
    
    /**
     * Loads a String (pre-determined user command) into console
     * @param command   pre-set command to insert into console
     */
    @Override
    public void loadInput(String command) {
        console.appendText(command);    // "types" long command into textbox for the ability to re-use a pre-defined function
    }

    private void addElements()  {
        List<Node> elements = new ArrayList<Node>();

        addText(elements);
        //addButtons(elements);

        this.getChildren().addAll(elements);
    }

    protected void printResult(String res)    {
        history.appendText("\n" + res);
    }

    private void addText(List<Node> elements)  {
        history.setEditable(false);
        history.setPromptText("Command History");
        history.setPrefWidth(width);
        history.setPrefHeight(hHeight);
        history.setMaxHeight(history.USE_PREF_SIZE);
        elements.add(history);

        console.setPromptText("Enter Commands");
        console.setPrefWidth(width);
        console.setPrefHeight(cHeight);
        console.setLayoutY(Math.max(hHeight, history.getMinHeight()) + offsetPad);
        elements.add(console);
    }

    private void checkSpecial(String comm)  {
        if (comm.matches("^(?i)" + ResourcesLanguages.getString(language, "MakeUserInstruction") +"(?s).*?"))   {
            makeUDI(comm);
        }

        if (comm.matches("^(?i)" + ResourcesLanguages.getString(language, "MakeVariable").split("\\|")[0] +"(?s).*?")
          || comm.matches("^(?i)" + ResourcesLanguages.getString(language, "MakeVariable").split("\\|")[1] +"(?s).*?"))   {
            makeVariable(comm);
        }
    }

    private void makeUDI(String comm)  {
        ((SideBar)myVBox).addButton(comm);
    }

    private void makeVariable(String comm) {
        ((SideBar)myVBox).addVar(comm.split(" ")[1], comm.split(" ")[2]);
    }

    private void addButtons(List<Node> elements)   {
        elements.add(runner);
        elements.add(clearer);
    }

    private Button setRunner() {
        Button aRunner = new Button("Run");

        aRunner.setLayoutX(width + offsetPad);
        aRunner.setPrefWidth(bWidth);
        aRunner.setMaxWidth(runner.USE_PREF_SIZE);
        aRunner.setPrefHeight(bHeight);
        aRunner.setMaxHeight(aRunner.USE_PREF_SIZE);
        aRunner.setTextAlignment(TextAlignment.CENTER);
        aRunner.setFont(new Font(20));

        aRunner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                run();
            }
        });

        return aRunner;
    }

    private Button setClearer()    {
        Button aClearer = new Button("Clear");

        aClearer.setLayoutX(width + offsetPad);
        aClearer.setLayoutY(bHeight + offsetPad);
        aClearer.setPrefWidth(bWidth);
        aClearer.setMaxWidth(aClearer.USE_PREF_SIZE);
        aClearer.setPrefHeight(bHeight);
        aClearer.setMaxHeight(aClearer.USE_PREF_SIZE);
        aClearer.setTextAlignment(TextAlignment.CENTER);
        aClearer.setFont(new Font(20));

        aClearer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                clear();
            }
        });

        return aClearer;
    }
}