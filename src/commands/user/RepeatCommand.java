package commands.user;

import commands.Commandable;
import commands.Result;

public class RepeatCommand implements Commandable {
	
	private Double count;

	@Override
	public Result execute() {
		count -= 1;
		// should be the last command that gets executed
		return new Result(0.0);
	}

	@Override
	public void inject(Double arg) {
		count = arg;
	}

}
