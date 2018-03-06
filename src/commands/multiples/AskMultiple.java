package commands.multiples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class AskMultiple extends Multiple {


	public AskMultiple(Visualizer vis, Parser parser, List<String> actives, Map<String, Updatable> updatables) {
		super(vis, parser, actives, updatables, 2);
	}

	@Override
	protected double manage(List<String> input) {
		List<String> old_actives = new ArrayList<>(getActives());
		int[] turtles = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1);
		replaceActives(input.subList(turtles[0] + 1, turtles[1]));
		double ans = getParser().parse(String.join(" ", input.subList(commands[0] + 1, commands[1])));
		replaceActives(old_actives);
		modifyList(input, commands[1]);
		return ans;
	}

	private void replaceActives(List<String> replace) {
		getActives().clear();
		for (int i=0; i < replace.size(); i++) {
			getActives().add(replace.get(i));
		}
	}

}
