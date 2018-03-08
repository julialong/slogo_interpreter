package slogo_team07;

import parser.Parser;

public class Test {
	
	public static void main(String[] args){
		Parser p = new Parser(null);
//		double d = p.parse("DOTIMES [ x 4 ] [ ifelse sum 1.0 0.0 [ lessp 4.0 5.0 ] [ lessp 5.0 4.0 ] ]");
//		double d = p.parse("DOTIMES [ x 0 ] [ sum 1 1 ] DOTIMES [ x 4 ] [ sum x x ]");
//		double d = p.parse("REPEAT sum 1 1 [ sum 2.0 2.0 ]");
//		double d = p.parse("for [ x 0 4 1.5 ] [ sum x x ]");
//		double d = p.parse("ifelse sum 1.0 0.0 [ lessp 4.0 5.0 ] [ lessp 5.0 4.0 ]");
//		double d = p.parse("sum if sum 1.0 0.0 [ lessp 4.0 5.0 ] 10.0");
//		double d = p.parse("");
//		double d = p.parse("sum 10 40");
		double d = p.parse("repeat sum 1 1 [ sum 2.0 2.0 ]");
	}
}