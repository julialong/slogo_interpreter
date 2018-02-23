package commands.math.algebra;

public class QuotientCommand extends AlgebraCommand {

	@Override
	protected Double calcValue(Double a, Double b) {
		return a / b;
	}

}
