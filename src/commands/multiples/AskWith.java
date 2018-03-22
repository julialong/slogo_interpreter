package commands.multiples;

import java.util.ArrayList;
import java.util.List;

import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

public class AskWith extends Multiple {
	
	private static final int NUM_ARGS = 2;
	
	private Parser myParser;
	private List<String> myActives;

	public AskWith(Visualizer vis, VariableReplacer var_replacer, Parser parser, List<String> actives) {
		super(vis, var_replacer, NUM_ARGS);
		myParser = parser;
		myActives = actives;
	}
	
	private double checkAndEval(List<String> input, int[] condition, int[] commands) {
		double ans = -1.0;
		for (String active : new ArrayList<>(myActives)) {
			activate(active);
			if (evaluate(input, condition) == 1.0) {
				ans = evaluate(input, commands);
			}
		}
		return ans;
	}
	
	private double evaluate(List<String> input, int[] brackets) {
		List<String> temp = new ArrayList<>(input);
		return myParser.parse(String.join(" ", temp.subList(brackets[0] + 1, brackets[1])));
	}

	private void activate(String word) {
		List<String> temp = new ArrayList<>();
		temp.add(word);
		activate(temp);
	}
	
	private void activate(List<String> replace) {
		myActives.clear();
		for (int i=0; i < replace.size(); i++) {
			myActives.add(replace.get(i));
		}
	}

	@Override
	protected double calcValue(List<String> args) {
		List<String> input = argsToList(args);
		List<String> old_actives = new ArrayList<>(myActives);
		int[] condition = findBrackets(input, 0);
		int[] commands = findBrackets(input, 1); 
		double ans = checkAndEval(input, condition, commands);
		activate(old_actives);
		modifyList(input, commands[1]);
		return ans;
	}
	
}
