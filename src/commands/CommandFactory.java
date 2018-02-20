package commands;

import slogo_team07.Argument;

public class CommandFactory<T1, T2> {
	
	public Commandable<?, ?, ?> createCommand(String command) {
		return createCommand(command, null);
	}
	
	public Commandable<?, ?, ?> createCommand(String command, T1 arg1) {
		return createCommand(command, arg1, null);
	}
	
	@SuppressWarnings("unchecked")
	public Commandable<?, ?, ?> createCommand(String command, T1 arg1, T2 arg2) {
		Argument<T1, T2> constructorArg = new Argument<>(arg1, arg2);
		if (command.equals("fd") || command.equals("forward")) {
			return new ForwardCommand((Argument<Double, Void>) constructorArg);
		} else if (command.equals("setxy") || command.equals("goto")) {
			return new SetXYCommand((Argument<Double, Double>) constructorArg);
		} else if (command.equals("home")) {
			return new HomeCommand((Argument<Void, Void>) constructorArg);
		} else if (command.equals("less") || command.equals("lessp")) {
			return new LessCommand((Argument<Double, Double>) constructorArg);
		} else {
			return new NullCommand((Argument<Void, Void>) constructorArg);
		}
	}
	
}
