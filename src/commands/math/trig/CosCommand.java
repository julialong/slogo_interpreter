package commands.math.trig;

import java.util.List;

public class CosCommand extends TrigCommand {

	public CosCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.cos(args.get(0));
	}

}
