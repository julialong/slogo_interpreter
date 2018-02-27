package parser;

import java.util.Map;

public class UnbundlerFactory {
	private static final String PREFIX_STRING = "model.Unbundlers.";
	private static final String SUFFIX_STRING = "Unbundler";

	public Unbundler createUnbundler(String control, Map<String, String> var_map, Map<String, String> func_map) {
		if (control.equals("FOR")) {
			return new ForUnbundler();
		} else if (control.equals("IF")) {
			return new IfUnbundler();
		} else if (control.equals("IFELSE")) {
			return new IfElseUnbundler();
		} else if (control.equals("REPEAT")) {
			return new RepeatUnbundler();
		} else if (control.equals("DOTIMES")) {
			return new DoTimesUnbundler();
		} else if (control.equals("MAKE") || control.equals("SET")) {
			return new MakeUnbundler(var_map);
		} else if (control.equals("TO")) {
			return new ToUnbundler(func_map);
		}
		return null;
	}
//		// will convert FOR to For
//		String capitalized = control.substring(0, 1) + control.substring(1).toLowerCase();
//		try {
//			Class<?> clazz = Class.forName(PREFIX_STRING + capitalized + SUFFIX_STRING);
//			Constructor<?> ctor = clazz.getConstructor();
//			return (Unbundler) ctor.newInstance();
//		} catch (Exception e) {
//			// I think that this should be NullCommand, and ExceptionCommand can be called
//			// in Engine when iterating
//			return new ExceptionUnbundler();
//		}
}
