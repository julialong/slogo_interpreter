package slogo_team07;

import java.util.Iterator;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Parser p = new Parser(cf);
		Iterator<Commandable> i = p.parse("sum 5.2 50.2 sum 15.2 5.2");
		while (i.hasNext()) {
			Commandable c = i.next();
			System.out.println("in test: " + c.execute());
		}
	}
}