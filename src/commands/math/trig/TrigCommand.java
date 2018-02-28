package commands.math.trig;

import commands.NonUpdatableCommand;
import view.Visualizer;

public abstract class TrigCommand extends NonUpdatableCommand {

	public TrigCommand(Visualizer vis) {
		super(vis, 1);
	}
}
