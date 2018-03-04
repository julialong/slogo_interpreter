package commands.booleans;

import java.util.List;

import view.Visualizer;

public class NotCommand extends BooleanCommand {
	
	public NotCommand(Visualizer vis) {
		super(vis, 1);
	}
	
	@Override
	protected double calcValue(List<Double> args) {
		Boolean b = args.get(0) == 0.0; 
		return boolToDouble(b);
	}
}
