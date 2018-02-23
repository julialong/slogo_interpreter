package commands.booleans;

public class AndCommand extends BooleanCommand {
	
	@Override
	protected boolean calcValue(Double a, Double b) {
		return a != 0.0 && b != 0.0;
	}
}
