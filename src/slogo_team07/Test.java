package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import commands.Result;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory();
		Commandable c = cf.createCommand("less", 12.0, 7.4);
		Result<?> ans = c.execute(new Argument<Void, Void>(null, null));
		System.out.println(ans.getRes1());
	}
}
