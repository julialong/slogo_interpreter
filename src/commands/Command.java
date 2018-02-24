package commands;

import java.util.ArrayList;
import java.util.List;

public abstract class Command implements Commandable {
	
	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();

	public Command(int num_args) {
		myArgsNeeded = num_args;
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
	
	protected List<Double> getArgs() {
		return myArgs;
	}

}
