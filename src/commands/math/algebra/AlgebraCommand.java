package commands.math.algebra;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;

public abstract class AlgebraCommand implements Commandable {

	private static final int NUM_ARGS = 2;

	private int myArgsInjected = 0;
	private Double myFirst;
	private Double mySecond;
	private Double ans;
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		ans = calcValue(myFirst, mySecond);
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
		}

		if (myArgsInjected == 0) {
			myFirst = arg;
		} else {
			mySecond = arg;
		}
		myArgsInjected += 1;
	}
	
	public Double getAns() {
		return ans;
	}
	
	protected int getArgsInjected() {
		return myArgsInjected;
	}

	protected abstract Double calcValue(Double a, Double b);
}
