package commands.math;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;

public class SumCommand implements Commandable {
	
	private static final int NUM_ARGS = 2;
	
	private int myArgsInjected = 0;
	private double myA;
	private double myB;

	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		return new Result(myA + myB);
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}
		
		if (myArgsInjected == 0) {
			myA = arg;
		} else {
			myB = arg;
		}
		myArgsInjected += 1;
	}

}
