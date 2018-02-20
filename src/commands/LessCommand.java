package commands;

import slogo_team07.Argument;

public class LessCommand implements Commandable<Void, Void, Integer> {
	
	private double expr1;
	private double expr2;
	
	public LessCommand(Argument<Double, Double> argument) {
		expr1 = argument.getArg1();
		expr2 = argument.getArg2();
	}

	@Override
	public Result<Integer> execute(Argument<Void, Void> argument) {
		int isLess = expr1 < expr2 ? 1 : 0;
		return new Result<Integer>(isLess);
	}

}
