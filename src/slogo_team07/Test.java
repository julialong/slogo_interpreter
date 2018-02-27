package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Parser p = new Parser(cf);
		Iterable<Commandable> iterable = p.parse("sum greaterp  11.4 10.4 sum random 4.3 5.2");
		for (Commandable c : iterable) {
			System.out.println("in test: " + c.execute());
		}
	}
}