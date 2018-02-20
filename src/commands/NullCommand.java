package commands;

import slogo_team07.Argument;

public class NullCommand implements Commandable {
	
	public NullCommand(Argument<Void, Void> argument) {}

	@Override
	public Result<?> execute(Argument<?, ?> argument) {
		// do nothing
		return new Result<Void>(null);
	}

}
