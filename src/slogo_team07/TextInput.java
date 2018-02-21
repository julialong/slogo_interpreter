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
	public void run();

	/**
	 * Clears all text in TextArea
	 */
	public void clear();

	/**
	 * Takes a command and appends it to any text in TextArea
	 * @param command	defined command to place into TextArea
	 */
	public void loadInput(String command);

	/**
	 * View previous liens of code, displayed above current code, in separeate unmodifiable TextArea
	 */
	public void scrollUp();

	/**
	 * View more recent lines of code. Display area lmiited by TextInput size, with older lines of code being replace by newer ones if there is no room left
	 * If limit of most recent line of code reached, will scroll until most recent line of code is at top of TextArea, then do nothing
	 */
	public void scrollDown();
}