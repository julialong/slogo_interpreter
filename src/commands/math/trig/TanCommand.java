package commands.math.trig;

import java.util.List;

public class TanCommand extends TrigCommand {

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.tan(args.get(0));
	}

}
