package commands.math.trig;

import java.util.List;

public class CosCommand extends TrigCommand {

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.cos(args.get(0));
	}

}
