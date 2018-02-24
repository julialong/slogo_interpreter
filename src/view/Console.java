/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Handles input of all commands from user in program. Has run, clear, and recall command functionality
 */

package view;

import java.util.List;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Console extends Pane implements TextInput {
    private TextArea console;
    private TextArea history;
    private Button runner;
    private Button clearer;
    private List<String> pastCommands;

    private int offsetPad = 9;
    private int width = 700;
    private int cHeight = 100;
    private int hHeight = 30;
    private int bHeight = (cHeight + hHeight + offsetPad)/2;
    private int bWidth = 50;
    private int commandIndex = -1;   // will track what command is "last" for scrollability

    /**
     * Constructor for Console
     */
    public Console()    {
        init();

        // this.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        //     @Override
        //     public void handle(KeyEvent event) {
        //          console.setPrefWidth();
        //          history.setPrefWidth();
        //     }
        // });
    }

    public Pane init() {
        console = new TextArea();
        history = new TextArea();
        runner = setRunner();
        clearer = setClearer();
        pastCommands = new ArrayList<>();

        addElements();

        return this;
    }

    private void addElements()  {
        List<Node> elements = new ArrayList<Node>();

        addText(elements);
        addButtons(elements);

        this.getChildren().addAll(elements);
    }

    private void addText(List<Node> elements)  {
        console.setPromptText("commands");
        console.setPrefWidth(width);
        console.setPrefHeight(cHeight);
        console.setLayoutY(Math.max(hHeight, history.getMinHeight()) + offsetPad);
        elements.add(console);

        history.setEditable(false);
        history.setPromptText("command history");
        history.setPrefWidth(width);
        history.setPrefHeight(hHeight);
        history.setMaxHeight(history.USE_PREF_SIZE);
        elements.add(history);
    }

    private void addButtons(List<Node> elements)   {
        elements.add(runner);
        elements.add(clearer);
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
        console.appendText("\n" + command);    // "types" long command into textbox for the ability to re-use a pre-defined function
    }

    private Button setRunner() {
        Button aRunner = new Button("Run");

        aRunner.setLayoutX(width + offsetPad);
        aRunner.setPrefWidth(bWidth);
        aRunner.setMaxWidth(runner.USE_PREF_SIZE);
        aRunner.setPrefHeight(bHeight);
        aRunner.setMaxHeight(aRunner.USE_PREF_SIZE);
        aRunner.setTextAlignment(TextAlignment.CENTER);

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
        aClearer.setLayoutY(bHeight);
        aClearer.setPrefWidth(bWidth);
        aClearer.setMaxWidth(aClearer.USE_PREF_SIZE);
        aClearer.setPrefHeight(bHeight);
        aClearer.setMaxHeight(aClearer.USE_PREF_SIZE);
        aClearer.setTextAlignment(TextAlignment.CENTER);
        aClearer.setFont(new Font(11));

        aClearer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                clear();
            }
        });

        return aClearer;
    }
}