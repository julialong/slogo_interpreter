package commands.factory;

/**
 * 
 * @author benhubsch
 *
 *         This is an exception class that gets thrown when a given Factory
 *         class doesn't recognize the input as being part of its Command bank.
 * 
 */
public class CommandNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8601094802043038638L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public CommandNotFoundException(String message) {
		super(String.format(message));
	}

	/**
	 * Create an exception based on an issue in our code.
	 */
	public CommandNotFoundException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public CommandNotFoundException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public CommandNotFoundException(Throwable exception) {
		super(exception);
	}
}
