package commands.booleans;

import java.util.List;

import view.Visualizer;

public class Less extends Boolean {

	public Less(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	protected double calcValue(List<Double> args) {
		boolean b = args.get(0) < args.get(1);
		return boolToDouble(b);
	}
}
