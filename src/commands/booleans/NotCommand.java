package commands.booleans;

import java.util.List;

public class NotCommand extends BooleanCommand {
	
	public NotCommand() {
		super(1);
	}
	
	@Override
	protected boolean calcValue(List<Double> args) {
		return args.get(0) == 0.0;
	}
}
