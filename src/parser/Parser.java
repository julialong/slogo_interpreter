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
import unbundler.UnbundlerFactory;

public class Parser implements Iterable<Commandable> {

	private static final String[] CONTROL_NAMES = { "make", "set", "for", "ifelse", "if", "repeat", "dotimes", "to" };
	private static final String NULL = "null";
	private static final String NUMBER = "number";

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
		myUnbundlerFactory = new UnbundlerFactory(myCommandFactory);
		myControlSet = new HashSet<>(Arrays.asList(CONTROL_NAMES));
	}

	public Iterable<Commandable> parse(String s) {
		myStringList = replaceUnknowns(s, myVarMap, myFuncMap);

		myDex = 0;
		myDummyRoot = new Node(myCommandFactory.createCommand(NULL));
		myCurrent = myDummyRoot;
		return () -> iterator();
	}

	@Override
	public Iterator<Commandable> iterator() {
		return new Iterator<Commandable>() {

			@Override
			public boolean hasNext() {
				return (myCurrent.getParent() != myDummyRoot && myCurrent != myDummyRoot)
						|| myDex < myStringList.size();
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
					if (myControlSet.contains(next.toLowerCase())) {
						Unbundler unbundler = myUnbundlerFactory.createUnbundler(next, myVarMap, myFuncMap);
						String unbundled = unbundler.unbundle(myStringList, myDex);
						System.out.println("main: " + unbundled);
						System.out.println(myStringList);
						if (unbundled.length() == 0) {
							return myCommandFactory.createCommand(NULL);
						}
						Iterable<Commandable> i = new Parser(myCommandFactory).parse(unbundled);
						Double ans = null;
						for (Commandable c : i) {
							c.execute();
							ans = c.getAns();
							System.out.println(c + " in parser findNext(): " + ans);
						}
						if (ans != null && myCurrent != myDummyRoot) {
							myCurrent.inject(ans);
							return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
						} else {
							return myCommandFactory.createCommand(NULL);
						}
					} else if (isCommand(next)) {
						myCurrent = createNode(next, myCurrent);
						myDex += 1;
						return findNext();
					} else if (isArgument(next)) {
						Commandable number = myCommandFactory.createCommand(NUMBER);
						number.inject(Double.valueOf(next));
						myDex += 1;
						return number;
					} else {
						return myCommandFactory.createCommand(NULL);
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
					System.out.println("next: " + unbundled);
					System.out.println(myStringList);
					if (unbundled.length() == 0) {
						return myCommandFactory.createCommand(NULL);
					}
					Iterable<Commandable> i = new Parser(myCommandFactory).parse(unbundled);
					Double ans = null;
					for (Commandable c : i) {
						c.execute();
						ans = c.getAns();
						System.out.println(c + " in parser findNext(): " + ans);
					}
					if (ans != null && myCurrent != myDummyRoot) {
						myCurrent.inject(ans);
						return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
					} else {
						return myCommandFactory.createCommand(NULL);
					}
				} else if (isCommand(curr)) {
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
		System.out.println(Arrays.toString(arr));
		List<String> ans = new ArrayList<>();
		int i=0;
		while (i < arr.length) {
			String curr = arr[i];
			if (var_map.containsKey(curr)) {
				String replaced = var_map.get(curr);
				ans.add(replaced);
				i += 1;
			} else if (func_map.containsKey(curr)) {
				Function func = func_map.get(curr);
				for (int j=0; j < func.numArgs(); j++) {
					func.inject(arr[i + j + 1]);
				}
				ans.addAll(func.replaceParams());
				i = i + func.numArgs() + 1; 
			} else {
				ans.add(curr);
				i += 1;
			}
		}
		System.out.println(Arrays.toString(ans.toArray()));
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
	 * Checks to determine if the given string is a number argument
	 * @param string is the argument
	 * @return true if the string is an argument
	 */
	private Boolean isArgument(String string) {
		return string.matches("-?[0-9]+\\.?[0-9]*");
	}


}
