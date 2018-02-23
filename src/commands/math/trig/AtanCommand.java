package commands.math.trig;

public class AtanCommand extends TrigCommand {

	@Override
	protected Double calcValue(Double a) {
		return Math.atan(a);
	}

}
