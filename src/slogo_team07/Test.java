package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory();
		Commandable less = cf.createCommand("not");
		less.inject(0.0);
		System.out.println(less.execute().toString());
	}
}
