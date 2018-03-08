package parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sanitizer {
	
	private Map<String, String> myVarMap;
	private Map<String, Function> myFuncMap;
	
	public Sanitizer(Map<String, String> var_map, Map<String, Function> func_map) {
		myVarMap = var_map;
		myFuncMap = func_map;
	}

	public List<String> sanitize(String s) {
		String commentless = stripComments(s);
		String whitespaced = handleWhitespace(commentless);
		String replaced = replaceUnknowns(whitespaced);
		return splitAroundBrackets(replaced);
	}
	
	private String replaceUnknowns(String whitespaced) {
		System.out.println("here: " + whitespaced);
		System.out.println("here1: " + myVarMap);
		List<String> ans = new LinkedList<>();
		if (whitespaced.length() == 0) {
			String.join(" ", ans);
		}
		
		String[] arr = whitespaced.split(" ");
		int i = 0;
		System.out.println(myFuncMap);
		while (i < arr.length) {
			String curr = arr[i];
			if (myVarMap.containsKey(curr)) {
				String replaced = myVarMap.get(curr);
				ans.add(replaced);
				i += 1;
			} else if (myFuncMap.containsKey(curr)) {
				System.out.println("inside " + curr);
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
		System.out.println("here2: " + ans);
		return String.join(" ", ans);
	}
	
	private List<String> splitAroundBrackets(String replaced) {
		List<String> split_brackets = new LinkedList<>();
		if (replaced.length() == 0) {
			return split_brackets;
		}
		
		String[] replaced_arr = replaced.split(" ");
		int i = 0;
		while (i < replaced_arr.length) {
			String curr = replaced_arr[i];
			if (curr.equals("[")) {
				int j = zoomAhead(replaced_arr, i);
				String[] copied = Arrays.copyOfRange(replaced_arr, i, j + 1);
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
}
