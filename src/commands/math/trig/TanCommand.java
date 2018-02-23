package commands.math.trig;

public class TanCommand extends TrigCommand {

	@Override
	protected Double calcValue(Double a) {
		return Math.tan(a);
	}

}
