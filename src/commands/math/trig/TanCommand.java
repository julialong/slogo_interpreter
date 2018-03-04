package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class TanCommand extends TrigCommand {

	public TanCommand(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.tan(args.get(0));
	}

}
