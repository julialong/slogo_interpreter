package parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import commands.Command;
import commands.CommandFactory;
import commands.unbundler.Unbundler;
import multiples.MultipleFactory;
import parser.unbundler.UnbundlerFactory;
import slogo_team07.Updatable;

public class Parser {

	private static final String[] CONTROL_NAMES = { "make", "set", "for", "ifelse", "if", "repeat", "dotimes", "to" };
	private static final String[] MANAGER_NAMES = { "tell", "ask", "askwith", "turtles" };

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
		List<String> input = sanitize(s);
		
		double ans = 0.0;
		while (!input.isEmpty()) {
			ans = Double.parseDouble(traverse(input));
		}
		return ans;
	}

	private String traverse(List<String> input) {
		if (input.isEmpty()) {
			return null;
		}

		String next = input.remove(0).toLowerCase();
		if (isArgument(next)) {
			return next;
		} else if (myControlSet.contains(next)) {
			Unbundler unbundler = myUnbundlerFactory.createUnbundler(next, myVarMap, myFuncMap);
			String unbundled = unbundler.unbundle(input);
			return String.valueOf(parse(unbundled));
		}

		List<Updatable> actives = myMultipleFactory.getActives();
		String ans = null;
		List<String> temp = input;
		List<Command> commandables = myCommandFactory.createCommands(next, actives);
		for (Command node : commandables) {
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

	private List<String> sanitize(String s) {
		String commentless = stripComments(s);
		String whitespaced = handleWhitespace(commentless);
		String replaced = replaceUnknowns(whitespaced);
		return splitAroundBrackets(replaced);
	}
	
	private String replaceUnknowns(String whitespaced) {
		List<String> ans = new LinkedList<>();
		if (whitespaced.length() == 0) {
			String.join(" ", ans);
		}
		
		String[] arr = whitespaced.split(" ");
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
		return String.join(" ", ans);
	}
	
	private List<String> splitAroundBrackets(String whitespaced) {
		List<String> split_brackets = new LinkedList<>();
		String[] white_arr = whitespaced.split(" ");
		int i = 0;
		while (i < white_arr.length) {
			String curr = white_arr[i];
			if (curr.equals("[")) {
				int j = zoomAhead(white_arr, i);
				String[] copied = Arrays.copyOfRange(white_arr, i, j + 1);
				split_brackets.add(String.join(" ", copied));
				i = j;
			} else {
				split_brackets.add(curr);
			}
			
			i += 1;
		}
		return split_brackets;
	}

	private int zoomAhead(String[] white_arr, int i) {
		int unmatched = 0;
		int j = i;
		while (j < white_arr.length) {
			String curr = white_arr[j];
			if (curr.equals("[")) {
				unmatched += 1;
			} else if (curr.equals("]")) {
				unmatched -= 1;
				if (unmatched == 0) {
					return j;
				}
			}
			j += 1;
		}
		return -1;
	}

	private String handleWhitespace(String commentless) {
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
