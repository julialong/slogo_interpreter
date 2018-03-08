package commands.factory;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import commands.Command;
import view.Visualizer;

public class NonUpdatableFactory implements Factory {
	
	private CommandFactory myCF;
	
	public NonUpdatableFactory(CommandFactory command_factory) {
		myCF = command_factory;
	}

	@Override
	public List<Command> create(String keyword) {
		Command command = null;
		try {
			Class<?> clazz = Class.forName(myCF.getBundleValue(keyword));
			Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] { Visualizer.class });
			command = (Command) ctor.newInstance(myCF.getVis());
		} catch (Exception e) {
			throw new CommandNotFoundException("NonUpdatable command not found.");
		}
		return Arrays.asList(command);
	}

}
