package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class AtanCommand extends TrigCommand {

	public AtanCommand(Visualizer vis) {
		super(vis);
	}

	@Override
	protected double calcValue(List<Double> args) {
		return Math.atan(args.get(0));
	}

}
