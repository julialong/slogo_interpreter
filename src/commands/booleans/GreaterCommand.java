package commands.booleans;

import java.util.List;

public class GreaterCommand extends BooleanCommand {
	
	public GreaterCommand() {
		super(2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = args.get(0) > args.get(1); 
		return boolToDouble(b);
	}
}
