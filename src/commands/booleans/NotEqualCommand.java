package commands.booleans;

import java.util.List;

public class NotEqualCommand extends BooleanCommand {

	public NotEqualCommand() {
		super(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean calcValue(List<Double> args) {
		return args.get(0) != args.get(1);
	}
}
