package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class ForwardCommand extends UpdatableCommand {
	
	public ForwardCommand(Visualizer vis, Updatable updatable) {
		super(vis, updatable, 1);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.move(args.get(0));
	}
	
}
