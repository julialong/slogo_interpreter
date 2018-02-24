package commands.updatable;

import java.util.List;

import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class HideUpdatable extends UpdatableCommand {

	public HideUpdatable(Updatable updatable) {
		super(0, updatable);
	}

	@Override
	protected Double calcValues(Updatable updatable, List<Double> args) {
		return updatable.setVisible(false);
	}

}
