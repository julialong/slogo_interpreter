package commands;

public class ExceptionCommand extends Command {

	private Double ans;
	
	public ExceptionCommand() {
		super(0);
	}

	@Override
	public Result execute() {
		return new Result(Double.MAX_VALUE);
	}

	public double getAns()	{
		return ans;
	}
}
