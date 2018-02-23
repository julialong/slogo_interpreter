package commands.math.algebra;

public class DifferenceCommand extends AlgebraCommand {

	@Override
	protected Double calcValue(Double a, Double b) {
		return a - b;
	}

}
