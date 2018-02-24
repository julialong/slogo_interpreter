package commands;

public class CommandArgsUnfilledException extends RuntimeException {
	
	private static final long serialVersionUID = 2710644981892984866L;
	
	 /**
     * Create an exception based on an issue in our code.
     */
    public CommandArgsUnfilledException(String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public CommandArgsUnfilledException(Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public CommandArgsUnfilledException(Throwable exception) {
        super(exception);
    }
}
