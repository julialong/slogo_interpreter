package commands;

public class ExceptionCommand extends Command {

	private Double ans;
	
	public ExceptionCommand() {
		super(Integer.MAX_VALUE);
	}

	@Override
	public Result execute() {
		return new Result(Double.MAX_VALUE);
	}
	
	@Override 
	public boolean isReady() {
		return false;
	}

	@Override
	public Double getAns() {
		// TODO Auto-generated method stub
		return null;
	}
}
