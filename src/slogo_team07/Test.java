package slogo_team07;

public class Test {
	
	private static Boolean isArgument(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*") || string.matches("^\\[.*]$");
	}
	
	public static void main(String[] args){
//		CommandFactory cf = new CommandFactory(null);
//		Parser p = new Parser(cf, null);
//		String s = p.parse("DOTIMES [ x 4 ] [ ifelse sum 1.0 0.0 [ lessp 4.0 5.0 ] [ lessp 5.0 4.0 ] ]");
//		String s = p.parse("DOTIMES [ x 0 ] [ sum 1 1 ] DOTIMES [ x 4 ] [ sum x x ]");
//		String s = p.parse("REPEAT sum 1 1 [ sum 2.0 2.0 ]");
//		String s = p.parse("for [ x 0 4 1.5 ] [ sum x x ]");
//		String s = p.parse("ifelse sum 1.0 0.0 [ lessp 4.0 5.0 ] [ lessp 5.0 4.0 ]");
//		String s = p.parse("sum if sum 1.0 0.0 [ lessp 4.0 5.0 ] 10.0");
//		String s = p.parse("");
//		String s = p.parse("sum 10 40");
//		String s = p.parse("lessp 10 48 lessp 10 48 lessp 10 48 lessp 10 48");
		System.out.println(isArgument("[ ifelse sum 1.0 0.0 [ lessp 4.0 5.0 ] [ lessp 5.0 4.0 ] ]"));
	}
}