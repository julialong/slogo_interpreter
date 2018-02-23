package commands.booleans;

public class GreaterCommand extends BooleanCommand {
	
	@Override
	protected boolean calcValue(Double a, Double b) {
		return a > b;
	}
}
