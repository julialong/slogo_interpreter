package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import commands.CommandFactory;
import commands.Commandable;
import unbundler.Unbundler;

public class Parser implements Iterable<Commandable> {

	private static final String[] CONTROL_NAMES = { "MAKE", "SET", "FOR", "IFELSE", "IF", "REPEAT", "DOTIMES", "TO" };

	private CommandFactory myCommandFactory;
	private UnbundlerFactory myUnbundlerFactory;
	private List<String> myStringList;
	private int myDex;
	private Node myDummyRoot;
	private Node myCurrent;
	private Set<String> myControlSet;
	private Map<String, String> myVarMap = new HashMap<>();
	private Map<String, Function> myFuncMap = new HashMap<>();

	public Parser(CommandFactory cf) {
		myCommandFactory = cf;
		myUnbundlerFactory = new UnbundlerFactory(this);
		myControlSet = new HashSet<>(Arrays.asList(CONTROL_NAMES));
	}

	public Iterable<Commandable> parse(String s) {
//		myStringList = replaceUnknowns(s, myVarMap, myFuncMap);
		myStringList = Arrays.asList(s.split(" "));

		myDex = 0;
		myDummyRoot = new Node(myCommandFactory.createCommand("null"));
		myCurrent = myDummyRoot;
		return () -> iterator();
	}

	@Override
	public Iterator<Commandable> iterator() {
		return new Iterator<Commandable>() {

			@Override
			public boolean hasNext() {
				return myCurrent.getParent() != myDummyRoot || myDex < myStringList.size();
			}

			@Override
			public Commandable next() {
				if (myCurrent.isReady() && myCurrent != myDummyRoot) { // means that we just executed a command we need the result of
					Double ans = myCurrent.getAns();
					myCurrent = myCurrent.getParent();
					myCurrent.inject(ans);
					return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
				} else {
					String next = myStringList.get(myDex);
					if (myControlSet.contains(next)) {
						Unbundler unbundler = myUnbundlerFactory.createUnbundler(next, myVarMap, myFuncMap);
						// need unbundled to rip out the commandflow stuff and return an array of Strings / string most likely
						String unbundled = unbundler.unbundle(myStringList, myDex);
						System.out.println(unbundled);
						Iterable<Commandable> i = new Parser(myCommandFactory).parse(unbundled);
						Double ans = null;
						for (Commandable c : i) {
							System.out.println("in parser: " + c.execute());
							ans = c.getAns();
						}
						myCurrent.inject(ans);
						return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
					} else {
						myCurrent = createNode(next, myCurrent);
						myDex += 1;
						return findNext();
					}
				}
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	private Commandable findNext() {
		while (!myCurrent.isReady()) {
			String curr = myStringList.get(myDex);
			if (isArgument(curr)) {
				myCurrent.inject(Double.parseDouble(curr));
			} else if (isCommand(curr)) {
				if (myControlSet.contains(curr)) {
					Unbundler unbundler = myUnbundlerFactory.createUnbundler(curr, myVarMap, myFuncMap);
					String unbundled = unbundler.unbundle(myStringList, myDex);
					Iterable<Commandable> i = new Parser(myCommandFactory).parse(unbundled);
					Double ans = null;
					for (Commandable c : i) {
						System.out.println("in parser: " + c.execute());
						ans = c.getAns();
					}
					myCurrent.inject(ans);
					return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
				} else {
					myCurrent = createNode(curr, myCurrent);
				}	
			}
			myDex += 1;
		}
		return myCurrent.getCommandable();
	}

	private Node createNode(String command, Node parent) {
		Commandable c = myCommandFactory.createCommand(command);
		Node new_node = new Node(c);
		new_node.setParent(myCurrent);
		parent.addToChildren(new_node);
		return new_node;
	}


	private List<String> replaceUnknowns(String s, Map<String, String> var_map, Map<String, Function> func_map) {
		String[] arr = s.split(" ");
		List<String> ans = new ArrayList<>();
		int i=0;
		while (i < arr.length) {
			String curr = arr[i];
			if (var_map.containsKey(curr)) {
				String replaced = var_map.get(curr);
				ans.add(replaced);
			} else if (func_map.containsKey(curr)) {
				Function func = func_map.get(curr);
				for (int j=0; j < func.numArgs(); j++) {
					func.inject(arr[i + j + 1]);
				}
				ans.addAll(func.replaceParams());
				i = i + func.numArgs() + 1; 
			} else {
				ans.add(curr);
			}
		}
		return ans;
	}

	/**
	 * Checks to determine if the given string is a command
	 * @param string is the argument
	 * @return true if the string is a Command
	 */
	private Boolean isCommand(String string) {
		return string.matches("[a-zA-Z_]+(\\?)?");
	}

	/**
	 * Checks for list structure required for control command
	 * @param string is the argument
	 * @return true if the string is a control argument
	 */
	private Boolean isControl(String string) {
		return (string.contains("[") && string.contains("]"));
	}

	/**
	 * Checks to determine if the given string is a number argument
	 * @param string is the argument
	 * @return true if the string is an argument
	 */
	private Boolean isArgument(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*");
	}

	/**
	 * Checks to determine if the given string is a variable
	 * @param string is the argument
	 * @return true if the string is an argument
	 */
	private Boolean isVariable(String string) {
		return string.matches(":[a-zA-Z_]+");
	}

	/**
	 * Checks to determine if the given string is a comment
	 * @param string is the argument
	 * @return true if the string is an argument
	 */
	private Boolean isComment(String string) {
		return string.matches("^#.*");
	}


}
