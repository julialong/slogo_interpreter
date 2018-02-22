package commands;

import commands.booleans.LessCommand;
import commands.turtle.ForwardCommand;
import commands.turtle.HomeCommand;
import commands.turtle.SetXYCommand;

public class CommandFactory {
	public Commandable createCommand(String command) {
		if (command.equals("fd") || command.equals("forward")) {
			return new ForwardCommand();
		} else if (command.equals("setxy") || command.equals("goto")) {
			return new SetXYCommand();
		} else if (command.equals("home")) {
			return new HomeCommand();
		} else if (command.equals("less") || command.equals("lessp")) {
			return new LessCommand();
		} else {
			return new NullCommand();
		}
	}	
}
