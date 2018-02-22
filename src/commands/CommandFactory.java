package commands;

import commands.turtle.ForwardCommand;
import commands.turtle.HomeCommand;
import commands.turtle.SetXYCommand;
import slogo_team07.Argument;

public class CommandFactory {
	
	public Commandable createCommand(String command) {
		return createCommand(command, null);
	}
	
	public <T> Commandable createCommand(String command, Double arg1) {
		return createCommand(command, arg1, null);
	}
	
	public Commandable createCommand(String command, Double arg1, Double arg2) {
		Argument constructorArg = new Argument(arg1, arg2);
		if (command.equals("fd") || command.equals("forward")) {
			return new ForwardCommand(constructorArg);
		} else if (command.equals("setxy") || command.equals("goto")) {
			return new SetXYCommand(constructorArg);
		} else if (command.equals("home")) {
			return new HomeCommand();
		} else if (command.equals("less") || command.equals("lessp")) {
			return new LessCommand(constructorArg);
		} else {
			return new NullCommand();
		}
	}
	
}
