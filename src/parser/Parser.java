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
import parser.unbundler.Unbundler;
import parser.unbundler.UnbundlerFactory;

public class Parser {

	private static final String[] CONTROL_NAMES = { "make", "set", "for", "ifelse", "if", "repeat", "dotimes", "to" };

	private CommandFactory myCommandFactory;
	private UnbundlerFactory myUnbundlerFactory;
	private Set<String> myControlSet;
	private Map<String, String> myVarMap = new HashMap<>();
	private Map<String, Function> myFuncMap = new HashMap<>();

	public Parser(CommandFactory cf) {
		myCommandFactory = cf;
		myUnbundlerFactory = new UnbundlerFactory(myCommandFactory);
		myControlSet = new HashSet<>(Arrays.asList(CONTROL_NAMES));
	}

	public Double parse(String s) {
		s = sanitize(s);
		List<String> input = replaceUnknowns(s);

		Double ans = null;
		while (!input.isEmpty()) {
			ans = traverse(input);
		}
		return ans;
	}

	public Double traverse(List<String> input) {
		if (input.isEmpty()) {
			return Double.MAX_VALUE;
		}

		String next = input.remove(0);
		if (isArgument(next)) {
			return Double.valueOf(next);
		} else if (myControlSet.contains(next.toLowerCase())) {
			Unbundler unbundler = myUnbundlerFactory.createUnbundler(next, myVarMap, myFuncMap);
			String unbundled = unbundler.unbundle(input);
			return parse(unbundled);
		}

		// I believe next should be a command at this point, if it's not an arg or controlflow
		// comments should have already been stripped
		Commandable node = myCommandFactory.createCommand(next);
		while (!node.isReady()) {
			node.inject(traverse(input));
		}
		node.execute();
		return node.getAns();
	}


	private List<String> replaceUnknowns(String s) {
		String[] arr = s.split(" ");
		List<String> ans = new LinkedList<>();
		int i=0;
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
				.collect(Collectors.joining());
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
