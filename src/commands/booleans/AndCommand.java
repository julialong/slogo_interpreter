package commands.booleans;

import java.util.List;

public class AndCommand extends BooleanCommand {

	public AndCommand() {
		super(2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = (args.get(0) != 0.0) && (args.get(1) != 0.0);
		return boolToDouble(b);
	}
}
