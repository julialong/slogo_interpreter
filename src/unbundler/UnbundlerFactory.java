package unbundler;

import java.util.Map;

import commands.CommandFactory;
import parser.Function;

public class UnbundlerFactory {
	
	private CommandFactory myCommandFactory;
	
	public UnbundlerFactory(CommandFactory cf) {
		myCommandFactory = cf;
	}

	public Unbundler createUnbundler(String control, Map<String, String> var_map, Map<String, Function> func_map) {
		if (control.equals("REPEAT")) {
			return new RepeatUnbundler(myCommandFactory);
		} else if (control.equals("DOTIMES")) {
			return new DoTimesUnbundler();
		} else if (control.equals("FOR")) {
			return new ForUnbundler();
		}
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
