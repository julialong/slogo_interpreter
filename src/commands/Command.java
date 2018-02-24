package commands;

import java.util.ArrayList;
import java.util.List;

public abstract class Command implements Commandable {
	
	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();
	private Double ans;

	public Command(int num_args) {
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
	
	protected List<Double> getArgs() {
		return myArgs;
	}
	
	protected abstract Double calcValue(List<Double> args);

}
