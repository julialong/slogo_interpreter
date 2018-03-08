package parser;

import java.util.ArrayList;
import java.util.Arrays;
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
			for (int j=0; j < replaced.size(); j++) {
				String command = replaced.get(j);
				if (command.equals(param)) {
					replaced.set(j, myArgs.get(i));
				}
			}
		}
		myArgs.clear();
		return replaced;
	}

	@Override
	public String toString() {
		return String.join(" ", myParams) + " | " + String.join(" ", myCommands);
	}
}
