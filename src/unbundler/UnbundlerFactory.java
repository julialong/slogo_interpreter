package unbundler;

import java.util.Map;

import commands.CommandFactory;
<<<<<<< HEAD:src/parser/UnbundlerFactory.java
import unbundler.*;
=======
import parser.Function;
>>>>>>> ben:src/unbundler/UnbundlerFactory.java

public class UnbundlerFactory {
	
	private CommandFactory myCommandFactory;
	
	public UnbundlerFactory(CommandFactory cf) {
		myCommandFactory = cf;
	}

	public Unbundler createUnbundler(String control, Map<String, String> var_map, Map<String, Function> func_map) {
		if (control.equals("REPEAT")) {
			return new RepeatUnbundler(myCommandFactory);
<<<<<<< HEAD:src/parser/UnbundlerFactory.java
		}  else if (control.equals("IF")) {
			return new IfUnbundler(myCommandFactory);
		} //else if (control.equals("IFELSE")) {
//			return new IfElseUnbundler();
//		}
 		else if (control.equals("FOR")) {
			return new ForUnbundler();
		} else if (control.equals("DOTIMES")) {
			return new DoTimesUnbundler();
		} //else if (control.equals("MAKE") || control.equals("SET")) {
=======
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
>>>>>>> ben:src/unbundler/UnbundlerFactory.java
//			return new MakeUnbundler(var_map);
//		} else if (control.equals("TO")) {
//			return new ToUnbundler(func_map);
//		}
		return null;
	}
}
