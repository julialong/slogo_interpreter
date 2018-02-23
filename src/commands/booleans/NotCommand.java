package commands.booleans;

public class NotCommand extends BooleanCommand {
	
	private static final int NUM_ARGS = 1;

	@Override
	public boolean isReady() {
		return getArgsInjected() == NUM_ARGS;
	}

	@Override
	protected boolean calcValue() {
		return getExpr1() == 0.0;
	}
}
