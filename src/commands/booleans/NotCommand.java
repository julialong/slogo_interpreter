package commands.booleans;

public class NotCommand extends BooleanCommand {
	
	private static final int NUM_ARGS = 1;

	@Override
	public boolean isReady() {
		return getArgsInjected() == NUM_ARGS;
	}

	@Override
	protected boolean calcValue(Double a, Double b) {
		return a == 0.0;
	}
}
