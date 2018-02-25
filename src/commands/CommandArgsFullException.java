package commands;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommandArgsFullException extends RuntimeException {
	private static final long serialVersionUID = -2713828251237349502L;
	
	/**
     * Create an exception based on an issue in our code.
     */
    public CommandArgsFullException(String message) {
        super(String.format(message));
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsFullException");
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Create an exception based on an issue in our code.
     */
    public CommandArgsFullException(String message, Object ... values) {
        super(String.format(message, values));
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsFullException");
        alert.setContentText(message + ".\nValues: " + values);
        alert.show();
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public CommandArgsFullException(Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsFullException");
        alert.setContentText(message + ".\nCause: " + cause + ".\nValues: " + values);
        alert.show();
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public CommandArgsFullException(Throwable exception) {
        super(exception);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsFullException");
        alert.setContentText("Exception " + exception.toString());
        alert.show();
    }

}
