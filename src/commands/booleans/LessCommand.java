package commands.booleans;

import commands.Commandable;
import commands.Result;

public class LessCommand implements Commandable {
	
	private static final int NUM_ARGS = 2;
	
	private int myArgsInjected = 0;
	private double expr1;
	private double expr2;

	@Override
	public Result execute() {
		Double isLess = expr1 < expr2 ? 1.0 : 0.0;
		return new Result(isLess);
	}

	@Override
	public boolean isReady() {
		return myArgsInjected == NUM_ARGS;
	}

	@Override
	public void inject(Double arg) {
		if (myArgsInjected == 0) {
			expr1 = arg;
		} else {
			expr2 = arg;
		}
		myArgsInjected += 1;
	}
}
