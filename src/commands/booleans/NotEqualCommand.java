package commands.booleans;

public class NotEqualCommand extends BooleanCommand {

	@Override
	protected boolean calcValue(Double a, Double b) {
		return a != b;
	}
}
