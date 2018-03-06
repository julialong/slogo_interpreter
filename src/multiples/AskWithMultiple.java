package multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import parser.Parser;

//askwith [ greater? xcor 50 ] [
//                              lt random 360
//                              bk 100
//                        ]

public class AskWithMultiple extends Multiple {
	
	private Set<String> myActives;
	private Parser myParser;
	
	public AskWithMultiple(Set<String> actives, Parser p) {
		myActives = actives;
		myParser = p;
	}

	@Override
	public double manage(List<String> input) {
		List<String> old_actives = new ArrayList<>(myActives);
		int[] condition = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1); 
		double ans = -1.0;
		for (String active : new ArrayList<>(myActives)) {
			replaceActives(active);
			if (evaluate(input, condition) == 1.0) {
				ans = evaluate(input, commands);
			}
		}
		replaceActives(old_actives);
		modifyList(input, commands[1]);
		return ans;
	}
	
	private double evaluate(List<String> input, int[] brackets) {
		List<String> temp = new ArrayList<>(input);
		return myParser.parse(String.join(" ", temp.subList(brackets[0] + 1, brackets[1])));
	}

	private void replaceActives(String word) {
		List<String> temp = new ArrayList<>();
		temp.add(word);
		replaceActives(temp);
	}
	
	private void replaceActives(List<String> replace) {
		myActives.clear();
		for (int i=0; i < replace.size(); i++) {
			myActives.add(replace.get(i));
		}
	}
	
}
