package commands;

public class Result {
	private Double res1;

	public Result(double res1) {
		this.res1 = res1;
	}

	public double getRes1() {
		return res1;
	}

	@Override 
	public String toString() {
		return (res1 != null) ? res1.toString() : "";
	}
}
