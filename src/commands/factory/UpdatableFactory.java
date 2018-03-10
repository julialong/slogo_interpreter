package commands.factory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

public class UpdatableFactory implements Factory {

	private CommandFactory myCF;
	
	public UpdatableFactory(CommandFactory command_factory) {
		myCF = command_factory;
	}

	@Override
	public List<Command> create(String keyword) {
		List<Command> commandables = new ArrayList<>();
		try {
			Class<?> clazz = Class.forName(myCF.getBundleValue(keyword));
			Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] { Visualizer.class, VariableReplacer.class, Updatable.class });
			for (String id : myCF.getActives()) {
				commandables.add((Command) ctor.newInstance(myCF.getVis(), myCF, myCF.getUpdatableById(id)));
			}
		} catch (Exception e) {
			throw new CommandNotFoundException("Updatable command not found.");
		}
		return commandables;
	}

}
