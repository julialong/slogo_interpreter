package parser;

import java.lang.reflect.Constructor;

import commands.Commandable;
import commands.ExceptionCommand;
import commands.UpdatableCommand;
import slogo_team07.Updatable;

public class UnbundlerFactory {
	private static final String PREFIX_STRING = "model.Unbundlers.";
	private static final String SUFFIX_STRING = "Unbundler";

	public Unbundler createUnbundler(String control) {
		// will convert FOR to For
		String capitalized = control.substring(0, 1) + control.substring(1).toLowerCase();
		try {
			Class<?> clazz = Class.forName(PREFIX_STRING + capitalized + SUFFIX_STRING);
			Constructor<?> ctor = clazz.getConstructor();
			return (Unbundler) ctor.newInstance();
		} catch (Exception e) {
			// I think that this should be NullCommand, and ExceptionCommand can be called
			// in Engine when iterating
			return new ExceptionUnbundler();
		}
	}
}
