package multiples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import commands.CommandFactory;
import slogo_team07.Updatable;
import view.Visualizer;

public class MultipleFactory {

	private int myIdCount = 0;
	private Set<String> myActives = new HashSet<>();
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Visualizer myVis;
	private CommandFactory myCommandFactory;
	
	public MultipleFactory(Visualizer vis) {
		myVis = vis;
	}

	public Multiple createMultiple(String command) {
		if (command.equals("tell")) {
			return new TellMultiple(myUpdatables, myVis, myActives);
		}
		return null;
	}

	public List<Updatable> getActives() {
		return myUpdatables.keySet().stream()
				.filter(myActives::contains)
				.map(myUpdatables::get)
				.collect(Collectors.toList());
	}
}
