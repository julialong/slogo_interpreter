package commands.booleans;

public class LessCommand extends BooleanCommand {

	@Override
	protected boolean calcValue(Double a, Double b) {
		return a < b;
	}
}
