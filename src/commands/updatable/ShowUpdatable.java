package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class ShowUpdatable extends UpdatableCommand {

	public ShowUpdatable(Updatable updatable) {
		super(0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setVisible(true);
	}

}
