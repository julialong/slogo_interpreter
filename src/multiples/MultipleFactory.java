package multiples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class MultipleFactory {

	private Set<String> myActives = new HashSet<>();
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Visualizer myVis;
	private Parser myParser;
	
	public MultipleFactory(Visualizer vis) {
		myVis = vis;
	}

	public Multiple createMultiple(String command) {
		if (command.equals("tell")) {
			return new TellMultiple(myUpdatables, myVis, myActives);
		} else if (command.equals("turtles")) {
			return new TurtlesMultiple(myUpdatables);
		} else if (command.equals("ask")) {
			return new AskMultiple(myActives, myParser);
		} else if (command.equals("askwith")) {
			return new AskWithMultiple(myActives, myParser);
		}
		return null;
	}

	public List<Updatable> getActives() {
		return myUpdatables.keySet().stream()
				.filter(myActives::contains)
				.map(myUpdatables::get)
				.collect(Collectors.toList());
	}

	public void injectParser(Parser parser) {
		myParser = parser;
	}
}
