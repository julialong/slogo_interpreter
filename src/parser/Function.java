package parser;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private List<String> myArgs = new ArrayList<>();
	private List<String> myParams;
	private List<String> myCommands;
	
	public Function(List<String> params, List<String> commands) {
		myParams = params;
		myCommands = commands;
	}
	
	public void inject(String arg) {
		System.out.println("arg: " + arg);
		myArgs.add(arg);
	}
	
	public int numArgs() {
		return myParams.size();
	}
	
	public List<String> replaceParams() {
		List<String> replaced = new ArrayList<>(myCommands);
		for (int i=0; i < myParams.size(); i++) {
			String param = myParams.get(i);
			for (String command : replaced) {
				if (command.equals(param)) {
					replaced.add(myArgs.get(i));
				} else {
					replaced.add(command);
				}
			}
		}
		myArgs.clear();
		return replaced;
	}
}
