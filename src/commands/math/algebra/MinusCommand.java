package commands.math.algebra;

public class MinusCommand extends AlgebraCommand {
	
	private static final int NUM_ARGS = 1;
	
	@Override
	public boolean isReady() {
		return getArgsInjected() == NUM_ARGS;
	}

	@Override
	protected Double calcValue(Double a, Double b) {
		return -a;
	}

}
