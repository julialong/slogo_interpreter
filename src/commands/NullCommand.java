package commands;

import slogo_team07.Argument;

public class NullCommand implements Commandable {
	
	public NullCommand(Argument<Void, Void> argument) {}

	@Override
	public <T1, T2> Result<?> execute(Argument<T1, T2> argument) {
		// do nothing
		return null;
	}

}
