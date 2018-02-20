package slogo_team07;

public class Argument<T1, T2> {
	private T1 arg1;
	private T2 arg2;
	
	public Argument(T1 arg1) {
		this(arg1, null);
	}
	
	public Argument(T1 arg1, T2 arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public T1 getArg1() {
		return arg1;
	}
	
	public T2 getArg2() {
		return arg2;
	}
}
