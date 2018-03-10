package parser;

public class NoCurrentTurtleException extends RuntimeException {

	private static final long serialVersionUID = 2611827151260613000L;

	/**
     * Create an exception based on an issue in our code.
     */
    public NoCurrentTurtleException(String message) {
        super(String.format(message));
    }

    /**
     * Create an exception based on an issue in our code.
     */
    public NoCurrentTurtleException(String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public NoCurrentTurtleException(Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public NoCurrentTurtleException(Throwable exception) {
        super(exception);
    }
}
