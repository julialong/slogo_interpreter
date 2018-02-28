package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Parser p = new Parser(cf);
//		Iterable<Commandable> iterable = p.parse("REPEAT 10. [ lessp 8.0 5.1 ]");
//		Iterable<Commandable> iterable = p.parse("sum 11 20 REPEAT 4 [ lessp 3.0 5.1 ]");
//		Iterable<Commandable> iterable = p.parse("sum 10 20 REPEAT sum 2.1 3.2 [ lessp 8.0 5.1 ] sum 10 20");
		Iterable<Commandable> iterable = p.parse("sum 2.1 3.2");
		for (Commandable c : iterable) {
			System.out.println("in test: " + c.execute());
		}
	}
}