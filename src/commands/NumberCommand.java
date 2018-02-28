package commands;

import java.util.List;

public class NumberCommand extends NonUpdatableCommand {

	public NumberCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return args.get(0);
	}

}
