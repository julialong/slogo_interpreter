package commands;

import java.util.List;

/**
 * Finds the location of matching brackets in a List
 * @author benhubsch, julialong
 */
public interface BracketFinder {

	/**
	 * Finds the outermost pair of matching brackets in the given exp List
	 * @param exp is the list of the entire expression
	 * @param pairNum is the number of the pair of brackets we are looking for
	 * @return an array that contains the indexes of the two matching brackets
	 */
	default int[] findBrackets(List<String> exp, int pairNum) {
		int[] answer = new int[] {-1, -1};
		int unmatched = 0;
		for (int i = 0; i < exp.size(); i++) {
			String curr = exp.get(i);
			if (curr.equals("[") && unmatched == 0 && pairNum == 0) {
					answer[0] = i;
				unmatched += 1;
			} else if (curr.equals("]")) {
				unmatched--;
				if (unmatched == 0 && pairNum == 0) {
					answer[1] = i;
					return answer;
				}
					pairNum--;
				}
			}
		return new int[0];
	}
}
