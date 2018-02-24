package commands.turtle;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;
import slogo_team07.Turtle;

public abstract class TurtleCommand implements Commandable {
	
	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();
	private Turtle myTurtle;
	
	public TurtleCommand(int num_args, Turtle turtle) {
		myArgsNeeded = num_args;
		myTurtle = turtle;
	}
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		return new Result(calcValues(myTurtle, myArgs));
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

	protected abstract Double calcValues(Turtle turtle, List<Double> args);
}
