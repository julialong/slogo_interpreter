package commands.booleans;

import java.util.List;

import view.Visualizer;

public class And extends Boolean {

	public And(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected double calcValue(List<Double> args) {
		boolean b = (args.get(0) != 0.0) && (args.get(1) != 0.0);
		return boolToDouble(b);
	}

}
