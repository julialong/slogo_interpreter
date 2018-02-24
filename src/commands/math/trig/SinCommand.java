package commands.math.trig;

import java.util.List;

public class SinCommand extends TrigCommand {

	public SinCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.sin(args.get(0));
	}

}
