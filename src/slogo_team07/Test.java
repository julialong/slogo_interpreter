package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Parser p = new Parser(cf);
		Iterable<Commandable> iterable = p.parse("REPEAT random 10. [ lessp 8.0 5.1 ]");
		for (Commandable c : iterable) {
			System.out.println("in test: " + c.execute());
		}
	}
}