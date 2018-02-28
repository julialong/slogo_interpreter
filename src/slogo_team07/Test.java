package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Parser p = new Parser(cf);
//		Iterable<Commandable> iterable = p.parse("DOTIMES [ x 4 ] [ sum x x ]");
		Iterable<Commandable> iterable = p.parse("DOTIMES [ x 4 ] [ sum 1 1 ] DOTIMES [ x 4 ] [ sum 1 1 ]");
//		Iterable<Commandable> iterable = p.parse("REPEAT sum 1 1 [ sum 1.0 1.0 ] REPEAT sum 1 1 [ sum 2.0 2.0 ]");
		for (Commandable c : iterable) {
			System.out.println("in test: " + c.execute());
		}
	}
}