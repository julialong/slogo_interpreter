package commands;

/**
 * 
 * @author benhubsch
 * 
 *         Result is used as a way of packaging the results of the Command
 *         objects to the front-end. It allows greater flexibility in the
 *         future, should requirements change and not all return values were
 *         numbers.
 *
 */
public class Result {
	private Double res1;

	public Result(double res1) {
		this.res1 = res1;
	}

	/**
	 * The front-end only relies on the toString() method, which allows us to
	 * encapsulate potential return value types.
	 */
	@Override
	public String toString() {
		return (res1 != null) ? res1.toString() : "";
	}
}
