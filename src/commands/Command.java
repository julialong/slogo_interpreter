package commands;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsFullException;
import commands.Commandable;

public abstract class Command implements Commandable {
	
	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();

	public Command(int num_args) {
		myArgsNeeded = num_args;
	}

	@Override
	public void inject(Double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}

		myArgs.add(arg);
	}

	protected boolean isReady() {
		return myArgs.size() == myArgsNeeded;
	}
	
	protected List<Double> getArgs() {
		return myArgs;
	}
	
	public int getChildren() {
		return myArgsNeeded;
	}

}
