package commands.math.algebra;

import commands.NonUpdatableCommand;
import view.Visualizer;

public abstract class AlgebraCommand extends NonUpdatableCommand {
	
	public AlgebraCommand(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
}
