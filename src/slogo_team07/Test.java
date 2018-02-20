package slogo_team07;

import commands.CommandFactory;
import commands.Commandable;
import commands.Result;

public class Test {
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory();
		Commandable c1 = cf.createCommand("less", 12.0, 7.4);
		Result<?> ans1 = c1.execute(new Argument<Void, Void>(null, null));
		System.out.println(ans1.getRes1());
		
		Commandable c2 = cf.createCommand("less", 4.3, 7.4);
		Result<?> ans2 = c2.execute(new Argument<Void, Void>(null, null));
		System.out.println(ans2.getRes1());
	}
}
