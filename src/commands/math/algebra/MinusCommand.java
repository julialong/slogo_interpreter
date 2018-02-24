package commands.math.algebra;

import java.util.List;

public class MinusCommand extends AlgebraCommand {
	
	public MinusCommand() {
		super(1);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return -args.get(0);
	}

}
