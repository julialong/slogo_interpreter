package commands;

public class NullCommand implements Commandable {

	@Override
	public Result execute() {
		// do nothing
		return new Result(null);
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void inject(Double arg) {}

}
