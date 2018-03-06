package commands.multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class AskWith extends Multiple {

	public AskWith(Visualizer vis, Parser parser, List<String> actives, Map<String, Updatable> updatables) {
		super(vis, parser, actives, updatables, 2);
	}

	@Override
	public double manage(List<String> input) {
		List<String> old_actives = new ArrayList<>(getActives());
		int[] condition = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1); 
		double ans = -1.0;
		for (String active : new ArrayList<>(getActives())) {
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
		return getParser().parse(String.join(" ", temp.subList(brackets[0] + 1, brackets[1])));
	}

	private void replaceActives(String word) {
		List<String> temp = new ArrayList<>();
		temp.add(word);
		replaceActives(temp);
	}
	
	private void replaceActives(List<String> replace) {
		getActives().clear();
		for (int i=0; i < replace.size(); i++) {
			getActives().add(replace.get(i));
		}
	}
	
}
