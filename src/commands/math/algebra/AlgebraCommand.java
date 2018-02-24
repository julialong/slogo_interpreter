package commands.math.algebra;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;

public abstract class AlgebraCommand implements Commandable {

	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();
	private Double ans;
	
	public AlgebraCommand(int num_args) {
		myArgsNeeded = num_args;
	}
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		ans = calcValue(myArgs);
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
		}

		myArgs.add(arg);
	}
	
	public Double getAns() {
		return ans;
	}

	protected abstract Double calcValue(List<Double> args);
}
