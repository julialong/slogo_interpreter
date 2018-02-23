package commands.math.algebra;

public class ProductCommand extends AlgebraCommand {

	@Override
	protected Double calcValue(Double a, Double b) {
		return a * b;
	}

}
