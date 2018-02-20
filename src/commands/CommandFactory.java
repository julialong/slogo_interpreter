package commands;

public class CommandFactory {
	public Commandable create(String command, double value) {
		if (command.equals("fd") || command.equals("forward")) {
			return new ForwardCommand(value);
		} else {
			return new NullCommand();
		}
	}
}
