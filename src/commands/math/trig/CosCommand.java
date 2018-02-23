package commands.math.trig;

public class CosCommand extends TrigCommand {

	@Override
	protected Double calcValue(Double a) {
		return Math.cos(a);
	}

}
