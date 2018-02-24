package commands;
public class CommandArgsFullException extends RuntimeException {
	private static final long serialVersionUID = -2713828251237349502L;
	
	/**
     * Create an exception based on an issue in our code.
     */
    public CommandArgsFullException(String message) {
        super(String.format(message));
    }

    /**
     * Create an exception based on an issue in our code.
     */
    public CommandArgsFullException(String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public CommandArgsFullException(Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public CommandArgsFullException(Throwable exception) {
        super(exception);
    }

}
