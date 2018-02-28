package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class CosCommand extends TrigCommand {

	public CosCommand(Visualizer vis) {
		super(vis);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.cos(args.get(0));
	}

}
