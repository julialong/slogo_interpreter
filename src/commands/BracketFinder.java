package commands;

import java.util.List;

/**
 * 
 * @author benhubsch, julialong
 *
 *         This is an interface that allows multiple classes to find the indices
 *         of any pair of brackets. For example, ask, askwith, for, and dotimes
 *         all take advantage of this implementation to accurately handle their
 *         inputs.
 */
public interface BracketFinder {

	String LEFT_BRACE = "[";
	String RIGHT_BRACE = "]";

	default int[] findBrackets(List<String> exp, int pairNum) {
		int[] answer = new int[] { -1, -1 };
		int unmatched = 0;
		for (int i = 0; i < exp.size(); i++) {
			String curr = exp.get(i);
			if (curr.equals(LEFT_BRACE)) {
				if (unmatched == 0 && pairNum == 0) {
					answer[0] = i;
				}
				unmatched += 1;
			} else if (curr.equals(RIGHT_BRACE)) {
				unmatched--;
				if (unmatched == 0) {
					if (pairNum == 0) {
						answer[1] = i;
						return answer;
					}
					pairNum--;
				}
			}
		}
		return new int[0];
	}

}
