package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null);
		Commandable less = cf.createCommand("less");
//		System.out.println(less.execute().toString());
	}
}