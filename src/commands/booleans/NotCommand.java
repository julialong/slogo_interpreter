package commands.booleans;

public class NotCommand extends BooleanCommand {
	
	private static final int NUM_ARGS = 1;
	
	private int myArgsInjected = 0;

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	protected boolean calcValue() {
		return getExpr1() == 0.0;
	}
}
