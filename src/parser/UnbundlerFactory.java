package parser;

import java.util.Map;

import unbundler.RepeatUnbundler;
import unbundler.Unbundler;

public class UnbundlerFactory {
	private static final String PREFIX_STRING = "model.Unbundlers.";
	private static final String SUFFIX_STRING = "Unbundler";
	
	private Parser myParser;
	
	public UnbundlerFactory(Parser parser) {
		myParser = parser;
	}

	public Unbundler createUnbundler(String control, Map<String, String> var_map, Map<String, Function> func_map) {
		if (control.equals("REPEAT")) {
			return new RepeatUnbundler(myParser);
		} // else if (control.equals("IF")) {
//			return new IfUnbundler();
//		} else if (control.equals("IFELSE")) {
//			return new IfElseUnbundler();
//		} else if (control.equals("FOR")) {
//			return new ForUnbundler();
//		} else if (control.equals("DOTIMES")) {
//			return new DoTimesUnbundler();
//		} else if (control.equals("MAKE") || control.equals("SET")) {
//			return new MakeUnbundler(var_map);
//		} else if (control.equals("TO")) {
//			return new ToUnbundler(func_map);
//		}
		return null;
	}
}
