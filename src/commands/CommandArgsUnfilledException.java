package commands;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommandArgsUnfilledException extends RuntimeException {
	
	private static final long serialVersionUID = 2710644981892984866L;
	
	 /**
     * Create an exception based on an issue in our code.
     */
    public CommandArgsUnfilledException(String message, Object ... values) {
        super(String.format(message, values));
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsUnfilledException");
        alert.setContentText(message + ".\nValues: " + values);
        alert.show();
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public CommandArgsUnfilledException(Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsUnfilledException");
        alert.setContentText(message + ".\nCause: " + cause + ".\nValues: " + values);
        alert.show();
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public CommandArgsUnfilledException(Throwable exception) {
        super(exception);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CommandArgsUnfilledException");
        alert.setContentText("Exception " + exception.toString());
        alert.show();
    }
}
