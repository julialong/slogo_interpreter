package commands.math.misc;

import commands.Commandable;
import commands.Result;

public class PiCommand implements Commandable {
	
	private Double ans;

	@Override
	public Result execute() {
		ans = Math.PI;
		return new Result(ans);
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void inject(Double arg) {}

}
