package commands.booleans;

import java.util.List;

public class LessCommand extends BooleanCommand {

	public LessCommand() {
		super(2);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		Boolean b = args.get(0) < args.get(1);
		return boolToDouble(b);
	}
}
