/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Handles input of all commands from user in program. Has run, clear, and recall command functionality
 */

package slogo_team07;

import java.util.List;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Console extends Group implements TextInput {
    private TextArea console;
    private TextArea history;
    private Button runner;
    private Button clearer;
    private Button languager;
    private List<String> pastCommands;

    private int offsetPad = 9;
    private int width = 500;
    private int cHeight = 100;
    private int hHeight = 30;
    private int bHeight = (cHeight + hHeight + offsetPad)/3;
    private int bWidth = 50;
    private int commandIndex = -1;   // will track what command is "last" for scrollability

    public Console()    {
        console = new TextArea();
        history = new TextArea();
        runner = setRunner();
        clearer = setClearer();
        languager = setLang();
        pastCommands = new ArrayList<>();

        addElements();
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
        history.setPrefWidth(width);
        history.setPrefHeight(hHeight);
        history.setMaxHeight(history.USE_PREF_SIZE);
        elements.add(history);
    }

    private void addButtons(List<Node> elements)   {
        elements.add(runner);
        elements.add(clearer);
        elements.add(languager);
    }

    @Override
    public void run()    {
        // new Interpretation().update(console.getText());
        pastCommands.add(console.getText());
        history.appendText("\n" + Integer.toString(pastCommands.size()) + ": " + console.getText());
        clear();
    }
    
    @Override
    public void clear() {
        console.clear();
    }
    
    @Override
    public void loadInput(String command) {
        console.appendText("\n" + command);    // "types" long command into textbox for the ability to re-use a pre-defined function
    }

    private Button setRunner() {
        Button aRunner = new Button("Run");

        aRunner.setLayoutX(500 + offsetPad);
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

        aClearer.setLayoutX(500 + offsetPad);
        aClearer.setLayoutY(bHeight);
        aClearer.setPrefWidth(bWidth);
        aClearer.setMaxWidth(aClearer.USE_PREF_SIZE);
        aClearer.setPrefHeight(bHeight);
        aClearer.setMaxHeight(aClearer.USE_PREF_SIZE);
        aClearer.setTextAlignment(TextAlignment.CENTER);

        aClearer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                clear();
            }
        });

        return aClearer;
    }

    private Button setLang()    {
        Button aLanguager = new Button("Change\nLanguage");

        aLanguager.setLayoutX(500 + offsetPad);
        aLanguager.setLayoutY(bHeight * 2);
        aLanguager.setPrefWidth(bWidth);
        aLanguager.setMaxWidth(aLanguager.USE_PREF_SIZE);
        aLanguager.setPrefHeight(bHeight);
        aLanguager.setMaxHeight(aLanguager.USE_PREF_SIZE);
        aLanguager.setFont(new Font(8));
        aLanguager.setTextAlignment(TextAlignment.CENTER);

        aLanguager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // change language
            }
        });

        return aLanguager;
    }
}