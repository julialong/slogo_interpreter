package commands.booleans;

import java.util.List;

import view.Visualizer;

public class Not extends Boolean {
	
	public Not(Visualizer vis) {
		super(vis, 1);
	}
	
	@Override
	protected double calcValue(List<Double> args) {
		boolean b = args.get(0) == 0.0; 
		return boolToDouble(b);
	}
}
