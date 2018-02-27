package parser;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private List<String> myParams;
	private List<String> myCommands;
	
	public Function(List<String> params, List<String> commands) {
		myParams = params;
		myCommands = commands;
	}
	
	public List<String> insertArgs(List<String> args) {
		List<String> replaced = new ArrayList<String>();
		for (int i=0; i < myParams.size(); i++) {
			String param = myParams.get(i);
			for (String command : myCommands) {
				if (command.equals(param)) {
					replaced.add(args.get(i));
				} else {
					replaced.add(command);
				}
			}
		}
		return replaced;
	}
}
