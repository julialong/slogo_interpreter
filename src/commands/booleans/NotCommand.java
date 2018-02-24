package commands.booleans;

import java.util.List;

public class NotCommand extends BooleanCommand {
	
	public NotCommand() {
		super(1);
	}
	
	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = args.get(0) == 0.0; 
		return boolToDouble(b);
	}
}
