package commands.math.trig;

import java.util.List;

import view.Visualizer;

public class SinCommand extends TrigCommand {

	public SinCommand(Visualizer vis) {
		super(vis);
	}

	@Override
	protected Double calcValue(List<Double> args) {
		return Math.sin(args.get(0));
	}

}
