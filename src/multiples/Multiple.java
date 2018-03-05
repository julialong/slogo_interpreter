package multiples;

import java.util.List;

public abstract class Multiple {
	public abstract double manage(List<String> input);
	
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
		return null;
	}
	
	protected void modifyList(List<String> exp, int dex) {
		for (int i=dex; i >= 0; i--) {
			exp.remove(i);
		}
	}
}
