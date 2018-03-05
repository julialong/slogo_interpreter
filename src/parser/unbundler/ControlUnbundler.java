package parser.unbundler;

import java.util.List;

import commands.CommandFactory;
import parser.Parser;

abstract class ControlUnbundler implements Unbundler{

	private static final String LEFT_BRACE = "[";
	private static final String RIGHT_BRACE = "]";
	
	/**
	 * Finds the beginning and ending brackets for the given control command
	 * @param exp
	 * @param index
	 * @return
	 */
	protected int[] findBrackets(List<String> exp, int pair_num) {
		int[] answer = new int[] {-1, -1};
		int unmatched = 0;
		for (int i = 0; i < exp.size(); i++) {
			String curr = exp.get(i);
			if (curr.equals("[")) {
				if  (unmatched == 0 && pair_num == 0) {
					answer[0] = i;
				}
				unmatched += 1;
			} else if (curr.equals("]")) {
				unmatched -= 1;
				if (unmatched == 0) {
					if (pair_num == 0) {
						answer[1] = i;
						return answer;
					}
					pair_num -= 1;
				}
			}
		}
		// well-formatted input should never reach here
		return null;
	}

	/**
	 * Modifies the list and returns a new list without the extracted, unbundled string
	 * @param exp is the entire ArrayList of the input commands
	 * @param lastIndex is the index where the command ends
	 */
	protected void modifyList (List < String > exp, int lastIndex) {
		for (int i = lastIndex; i >= 0; i--) {
			exp.remove(i);
		}
	}

	/**
	 * @param current is the current string
	 * @return true if the current string is not a left bracket, false otherwise
	 */
	protected boolean notLeftBracket (String current){
		return !current.equals(LEFT_BRACE);
	}

	/**
	 * @param current is the current string
	 * @return true if the current string is not a right bracket, false otherwise
	 */
	protected boolean notRightBracket (String current){
		return !current.equals(RIGHT_BRACE);
	}
}
