package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class HideTurtle extends UpdatableCommand {

	public HideTurtle(Visualizer vis, Updatable updatable) {
		super(vis, 0, updatable);
	}

	@Override
	protected double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setVisible(false);
	}

}