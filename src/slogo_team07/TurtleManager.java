package slogo_team07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commands.CommandFactory;
import commands.Commandable;
import view.Visualizer;

public class TurtleManager {

	private int myIdCount = 0;
	private List<String> myActives = new ArrayList<>();
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Visualizer myVis;
	private CommandFactory myCommandFactory;
	private Engine myEngine;
	
	public TurtleManager(Visualizer vis, Engine engine) {
		myVis = vis;
		myActives.addAll(myUpdatables.keySet());
		myCommandFactory = new CommandFactory(myUpdatables, myVis);
	}

	public List<Commandable> manage(String command) {
		command = command.toLowerCase();
		if (command.equals("id")) {
			
		}
		return null;
	}

	public void updateLanguage(String lang) {
		myCommandFactory.updateLanguage(lang);
	}

	public void addUpdatable(Updatable updatable) {
		myUpdatables.put(Integer.toString(myIdCount++), updatable);
	}
}
