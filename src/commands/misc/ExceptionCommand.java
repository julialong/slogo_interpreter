package commands.misc;

import commands.Command;
import view.Visualizer;

public class ExceptionCommand extends Command {

	private Double ans;
	
	public ExceptionCommand(Visualizer vis) {
		super(vis, 0);
	}

	@Override
	public void execute() {
		// do nothing for now, maybe pass an error message later
	}

	public Double getAns()	{
		return ans;
	}
}
