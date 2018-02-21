package slogo_team07;

public class HelpBox implements TextDisplayWindow {
    public void open()    {
        // make new Stage
        // insert help (command manual) text into new scene
        // display scene
    }
    
    public void close() {
        Stage toClose = this.getScene().getWindow();
        toClose.close();
    }
}