package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Parser p = new Parser(cf);
//		Iterable<Commandable> iterable = p.parse("DOTIMES [ x 2 ] [ sum 1 1 REPEAT sum x x [ sum 10 10 ] ] "); //DOES NOT WORK
		Iterable<Commandable> iterable = p.parse("IF sum 1 0 [ sum 10 20 ] sum 10 10");
//		Iterable<Commandable> iterable = p.parse("sum 10 20 DOTIMES [ x 4 ] [ sum 10 10 REPEAT sum 1 1 [ sum x x ] ] sum 10 20");
//		Iterable<Commandable> iterable = p.parse("sum 2.1 3.2");
		for (Commandable c : iterable) {
			System.out.println("in test: " + c.execute());
		}
	}
}