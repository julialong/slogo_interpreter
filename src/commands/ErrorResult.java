package commands;

public class ErrorResult extends Result {
	
	private String myErrorMessage;

	public ErrorResult(double res1, String message) {
		super(res1);
		myErrorMessage = message;
	}
	
	@Override 
	public String toString() {
		return myErrorMessage;
	}
}
