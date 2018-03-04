package slogo_team07;

import commands.CommandFactory;
import parser.Parser;

public class Test {
	
	public static void main(String[] args){
		CommandFactory cf = new CommandFactory(null, null);
		Parser p = new Parser(cf);
//		Double d = p.parse("DOTIMES [ x 4 ] [ sum x x ]");
//		Double d = p.parse("DOTIMES [ x 0 ] [ sum 1 1 ] DOTIMES [ x 4 ] [ sum x x ]");
//		Double d = p.parse("REPEAT sum 0. 0. [ sum 1.0 1.0 ] REPEAT sum 1 1 [ sum 2.0 2.0 ]");
		Double d = p.parse("for [ x 0 4 1 ] [ sum x x ]");
//		Double d = p.parse("repeat sum 1. 2.3 [ lessp 1.0 4.8 ]");
//		Double d = p.parse("lessp 10 48 lessp 10 48 lessp 10 48 lessp 10 48");
	}
}