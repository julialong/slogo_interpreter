/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Com
 */

package slogo_team07;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.TextArea;
import slogo_team07.TextInput;

public class Console implements TextInput {
    private TextArea console;
    private TextArea history;
    private List<String> pastCommands;

    private int width = 500;
    private int height = 100;
    private int commandIndex = -1;   // will track what command is "last" for scrollability

    public Console()    {
        console = new TextArea();
        history = new TextArea();
        pastCommands = new ArrayList<>();

        console.setPromptText("commands");
        console.setPrefWidth(width);
        console.setPrefHeight(height);
        history.setPrefWidth(width);
    }

    @Override
    public void run()    {
        // new Interpretation().update(console.getText());
        pastCommands.add(console.getText());
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
    
    @Override
    public void scrollUp() {
        if (pastCommands.isEmpty())   {
        }
        else if (commandIndex == -1)   {
            // have never scrolled before, so set index of last command to most recent
            commandIndex = pastCommands.size();
            scrollUp();
        }
        else if (commandIndex <= 0)  {
            // if reached first command (no more previous), leave display as first command
        }
        else    {
            history.setText(pastCommands.get(commandIndex--));
        }
    }
    
    @Override
    public void scrollDown() {
        if (pastCommands.isEmpty())   {
        }
        else if (commandIndex == -1)   {
            // have never scrolled before, so set index of last command to most recent
            commandIndex = pastCommands.size();
            scrollDown();
        }
        else if (commandIndex >= pastCommands.size())  {
            // if reached most recently submitted command (no more), leave display at most recent command
        }
        else    {
            history.setText(pastCommands.get(commandIndex++));
        }
    }
}