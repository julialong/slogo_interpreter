package commands.math.trig;

import java.util.List;

public class AtanCommand extends TrigCommand {

	public AtanCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.atan(args.get(0));
	}

}
