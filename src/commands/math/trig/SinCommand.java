package commands.math.trig;

public class SinCommand extends TrigCommand {

	@Override
	protected Double calcValue(Double a) {
		return Math.sin(a);
	}

}
