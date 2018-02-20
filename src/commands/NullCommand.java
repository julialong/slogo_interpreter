package commands;

import slogo_team07.Argument;

public class NullCommand implements Commandable<Void, Void, Void> {
	
	public NullCommand(Argument<Void, Void> argument) {}

	@Override
	public Result<Void> execute(Argument<Void, Void> argument) {
		return new Result<Void>(null);
	}

}
