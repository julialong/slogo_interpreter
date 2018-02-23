package commands.booleans;

public class EqualCommand extends BooleanCommand {
	
	@Override
	protected boolean calcValue(Double a, Double b) {
		return a == b;
	}
}
