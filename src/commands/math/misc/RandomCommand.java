package commands.math.misc;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;

public abstract class RandomCommand implements Commandable {
	
	private int myArgsNeeded = 1;
	private List<Double> myArgs = new ArrayList<>();
	private Double ans;

	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		ans = Math.random() * myArgs.get(0);
		return new Result(ans);
	}

	@Override
	public boolean isReady() {
		return myArgs.size() == myArgsNeeded;
	}

	@Override
	public void inject(Double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		} else if (arg < 0) {
			throw new IllegalArgumentException("Argument to RANDOM must be positive");
		}

		myArgs.add(arg);
	}

}
