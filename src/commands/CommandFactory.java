package commands;

//import slogo_team07.ForwardCommand;

public class CommandFactory {
	public Commandable create(String command, double value) {
		if (command.equals("fd")) {
			return new ForwardCommand(value);
		} else {
			return new NullCommand();
		}
	}
}
