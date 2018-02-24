package commands.booleans;

import java.util.List;

public class EqualCommand extends BooleanCommand {
	
	public EqualCommand() {
		super(2);
	}

	@Override
	protected boolean calcValue(List<Double> args) {
		return args.get(0) == args.get(1);
	}
}
