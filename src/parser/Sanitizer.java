package parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author benhubsch
 * 
 * This class is called when the user enters input and sanitizes the input, formatting it in
 * a way that the Parser expects and can handle.
 *
 */
public class Sanitizer {

	/**
	 * This is the entry point to the sanitizer. It takes a raw input string and forces everything
	 * to be singly-space separated, stripping out the comments and splitting the string around
	 * brackets. The string is split around brackets so that Command objects like DoTimes can have
	 * the list of command injected into them as a single entity.
	 *
	 * @param s the s
	 * @return List<String>
	 */
	public List<String> sanitize(String s) {
		String commentless = stripComments(s);
		String whitespaced = handleWhitespace(commentless);
		return splitAroundBrackets(whitespaced);
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
				.filter(line -> !line.trim().startsWith("#"))
				.collect(Collectors.joining(" "));
	}
}
