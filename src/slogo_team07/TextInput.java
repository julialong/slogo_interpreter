/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Interface that allows access and setting of any class containing a TextArea
 */

package slogo_team07;

public interface TextInput	{
	/**
	 * Sends text in TextArea as a String to an Interpreter for parsing, and clears TextArea
	 */
	public String run();

	/**
	 * Clears all text in TextArea
	 */
	public void clear();

	/**
	 * Takes a command and appends it to any text in TextArea
	 * @param command	defined command to place into TextArea
	 */
	public void loadInput(String command);
}