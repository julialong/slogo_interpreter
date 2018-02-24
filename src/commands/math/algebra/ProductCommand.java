package commands.math.algebra;

import java.util.List;

public class ProductCommand extends AlgebraCommand {

	public ProductCommand() {
		super(2);
	}
	
	@Override
	protected Double calcValue(List<Double> args) {
		return args.get(0) * args.get(1);
	}

}
