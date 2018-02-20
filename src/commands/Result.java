package commands;

public class Result<T1> {
	private T1 res1;
	
	public Result() {
		this(null);
	}
	
	public Result(T1 res1) {
		this.res1 = res1;
	}
	
	public T1 getRes1() {
		return res1;
	}
}
