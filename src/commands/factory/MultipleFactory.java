package commands.factory;

import java.util.Arrays;
import java.util.List;

import commands.Command;
import commands.multiples.Ask;
import commands.multiples.AskWith;
import commands.multiples.Tell;
import commands.multiples.Turtles;

public class MultipleFactory implements Factory {
	
	private CommandFactory myCF;
	
	public MultipleFactory(CommandFactory command_factory) {
		myCF = command_factory;
	}

	@Override
	public List<Command> create(String keyword) {
		Command command = null;
		if (keyword.equalsIgnoreCase("Ask")) {
			command = new Ask(myCF.getVis(), myCF.getParser(), myCF.getActives());
		} else if (keyword.equalsIgnoreCase("AskWith")) {
			command = new AskWith(myCF.getVis(), myCF.getParser(), myCF.getActives());
		} else if (keyword.equalsIgnoreCase("Tell")) {
			command = new Tell(myCF.getVis(), myCF.getActives(), myCF.getUpdatables());
		} else if (keyword.equalsIgnoreCase("Turtles")) {
			command = new Turtles(myCF.getVis(), myCF.getUpdatables());
		} else {
			throw new CommandNotFoundException("Multiple command not found.");
		}
		return Arrays.asList(command);
	}

}
