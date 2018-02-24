package commands.booleans;

import java.util.List;

public class OrCommand extends BooleanCommand {

	public OrCommand() {
		super(2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = (args.get(0) == 0.0 || args.get(1) == 0.0); 
		return boolToDouble(b);
	}
}
