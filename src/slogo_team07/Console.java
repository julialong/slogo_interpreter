/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Com
 */

package slogo_team07;

import javafx.scene.control.TextArea;

public class Console implements TextInput {
    TextArea myTextArea = new TextArea();
    myTextArea.setWidth(500);
    myTextArea.setHeight(100);

    public void run()    {
        new Interpretation().update(myTextArea.getText());
        clear();
    }
    
    public void clear() {
        myTextArea.clear();
    }
    
    public void loadInput(String command) {
        myTextArea.appendText("\n" + command));    // "types" long command into textbox for the ability to re-use a pre-defined function
    }
    
    public void scrollUp() {
        // view previous line of code, above currently displayed code, in the console
    }
    
    public void scrollDown() {
        // view following line of code, below currently displayed code, in the console
        // if no following line, do nothing
    }
}