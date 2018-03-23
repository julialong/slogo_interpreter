package commands;

/**
 * 
 * @author benhubsch
 * 
 *         This class is instantiated upon receiving an error during the parsing
 *         of an input. Its toString() is used to display an error message to
 *         the user in an alert box on the front end.
 *
 */
public class ErrorResult extends Result {

	private String myErrorMessage;

	public ErrorResult(double res1, String message) {
		super(res1);
		myErrorMessage = message;
	}

	@Override
	public String toString() {
		return "Error during parsing of input: " + myErrorMessage;
	}
}
