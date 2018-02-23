package commands.math.misc;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;

public abstract class RandomCommand implements Commandable {
	
	private static final int NUM_ARGS = 1;
	
	private int myArgsInjected = 0;
	private Double myFirst;
	private Double ans;

	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		ans = Math.random() * myFirst;
		return new Result(ans);
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		} else if (arg < 0) {
			throw new IllegalArgumentException("Argument to RANDOM must be positive");
		}

		myFirst = arg;
		myArgsInjected += 1;
	}

}
