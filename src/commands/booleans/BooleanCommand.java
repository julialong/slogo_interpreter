package commands.booleans;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;

public abstract class BooleanCommand implements Commandable {

	private static final int NUM_ARGS = 2;

	private int myArgsInjected = 0;
	private Double expr1;
	private Double expr2;
	
	private Double ans;

	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		Double or = calcValue() ? 1.0 : 0.0;
		ans = or;
		return new Result(or);
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
			expr1 = arg;
		} else {
			expr2 = arg;
		}
		myArgsInjected += 1;	
	}
	
	public Double getAns() {
		return ans;
	}
	
	protected Double getExpr1() {
		return expr1;
	}
	
	protected Double getExpr2() {
		return expr2;
	}
	
	protected int getArgsInjected() {
		return myArgsInjected;
	}

	protected abstract boolean calcValue();
}
