package slogo_team07;

public class Argument {
	private double arg1;
	private double arg2;
	
	public Argument(double arg1) {
		this(arg1, 0.0);
	}
	
	public Argument(double arg1, double arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public double getArg1() {
		return arg1;
	}
	
	public double getArg2() {
		return arg2;
	}
}


