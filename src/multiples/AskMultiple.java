package multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import parser.Parser;

public class AskMultiple extends Multiple {
	
	private Set<String> myActives;
	private Parser myParser;

	public AskMultiple(Set<String> actives, Parser p) {
		myActives = actives;
		myParser = p;
	}
	
	@Override
	public double manage(List<String> input) {
		List<String> old_actives = new ArrayList<>(myActives);
		int[] turtles = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1);
		replaceActives(input.subList(turtles[0] + 1, turtles[1]));
		double ans = myParser.parse(String.join(" ", input.subList(commands[0] + 1, commands[1])));
		replaceActives(old_actives);
		modifyList(input, commands[1]);
		return ans;
	}

	private void replaceActives(List<String> replace) {
		myActives.clear();
		for (int i=0; i < replace.size(); i++) {
			myActives.add(replace.get(i));
		}
	}

}
