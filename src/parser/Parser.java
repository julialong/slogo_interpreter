package parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import commands.CommandFactory;
import commands.Commandable;
import multiples.Multiple;
import multiples.MultipleFactory;
import parser.unbundler.Unbundler;
import parser.unbundler.UnbundlerFactory;
import slogo_team07.Updatable;

public class Parser {

	private static final String[] CONTROL_NAMES = { "make", "set", "for", "ifelse", "if", "repeat", "dotimes", "to" };
	private static final String[] MANAGER_NAMES = { "tell", "ask", "askwith", "turtles", "id" };

	private CommandFactory myCommandFactory;
	private UnbundlerFactory myUnbundlerFactory;
	private MultipleFactory myMultipleFactory;
	private Set<String> myControlSet;
	private Set<String> myMultiplesSet;
	private Map<String, String> myVarMap = new HashMap<>();
	private Map<String, Function> myFuncMap = new HashMap<>();

	public Parser(CommandFactory cf, MultipleFactory mf) {
		myCommandFactory = cf;
		myMultipleFactory = mf;
		myUnbundlerFactory = new UnbundlerFactory(myCommandFactory, this);
		myControlSet = new HashSet<>(Arrays.asList(CONTROL_NAMES));
		myMultiplesSet = new HashSet<>(Arrays.asList(MANAGER_NAMES));
	}

	public double parse(String s) {
		s = sanitize(s);
		
		List<String> input = replaceUnknowns(s);
		Double ans = 0.0;
		while (!input.isEmpty()) {
			ans = traverse(input);
		}
		return ans;
	}

	private double traverse(List<String> input) {
		if (input.isEmpty()) {
			return Double.MAX_VALUE;
		}

		String next = input.remove(0).toLowerCase();
		if (isArgument(next)) {
			return Double.valueOf(next);
		} else if (myControlSet.contains(next)) {
			Unbundler unbundler = myUnbundlerFactory.createUnbundler(next, myVarMap, myFuncMap);
			String unbundled = unbundler.unbundle(input);
			return parse(unbundled);
		} else if (myMultiplesSet.contains(next)) {
			Multiple multiple = myMultipleFactory.createMultiple(next);
			return multiple.manage(input);
		}

		List<Updatable> actives = myMultipleFactory.getActives();
		double ans = Double.MAX_VALUE;
		List<String> temp = input;
		List<Commandable> commandables = myCommandFactory.createCommands(next, actives);
		for (Commandable node : commandables) {
			temp = new LinkedList<>(input);
			while (!node.isReady()) {
				node.inject(traverse(temp));
			}
			ans = node.execute();
		}
		input.clear();
		for (String s : temp) {
			input.add(s);
		}
		return ans;
	}


	private List<String> replaceUnknowns(String s) {
		List<String> ans = new LinkedList<>();
		if (s.length() == 0) {
			return ans;
		}
		
		String[] arr = s.split(" ");
		int i = 0;
		while (i < arr.length) {
			String curr = arr[i];
			if (myVarMap.containsKey(curr)) {
				String replaced = myVarMap.get(curr);
				ans.add(replaced);
				i += 1;
			} else if (myFuncMap.containsKey(curr)) {
				Function func = myFuncMap.get(curr);
				for (int j=0; j < func.numArgs(); j++) {
					func.inject(arr[i + j + 1]);
				}
				ans.addAll(func.replaceParams());
				i = i + func.numArgs() + 1; 
			} else {
				ans.add(curr);
				i += 1;
			}
		}
		return ans;
	}

	private String sanitize(String s) {
		String commentless = stripComments(s);
		StringBuilder formatted = new StringBuilder();

		boolean seen_space = false;
		for (Character curr : commentless.toCharArray()) {
			if (!Character.isWhitespace(curr)) {
				formatted.append(curr);
				seen_space = false;
			} else {
				if (!seen_space) {
					formatted.append(" ");
					seen_space = true;
				}
			}
		}
		return formatted.toString().trim();
	}

	private String stripComments(String s) {
		return Arrays.asList(s.split("\n")).stream()
				.filter(line -> !line.startsWith("#"))
				.collect(Collectors.joining(" "));
	}

	/**
	 * Checks to determine if the given string is a number argument
	 * @param string is the argument
	 * @return true if the string is an argument
	 */
	private Boolean isArgument(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*");
	}


}
