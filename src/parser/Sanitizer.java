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
}
