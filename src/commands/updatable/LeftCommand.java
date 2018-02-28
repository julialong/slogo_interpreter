package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class LeftCommand extends UpdatableCommand {

	public LeftCommand(Visualizer vis, Updatable updatable) {
		super(vis, 0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		Double clockwise = convertToClockwise(args.get(0));
		return updatable.rotate(clockwise);
	}
	
	private Double convertToClockwise(double rotation) {
		return (-rotation + 90) % 360;
	}


}
