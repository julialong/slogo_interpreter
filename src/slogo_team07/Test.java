package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Commandable c = cf.createCommand("random");
		c.inject(8.0);
//		c.inject(4.9);
		System.out.println(c.execute().toString());
	}
}