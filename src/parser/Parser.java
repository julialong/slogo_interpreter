package parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import commands.CommandFactory;
import commands.Commandable;

public class Parser implements Iterable<Commandable> {
	
    private static final String[] CONTROL_NAMES = { "MAKE", "SET", "FOR", "IFELSE", "IF", "REPEAT", "DOTIMES", "TO" };
  
	private CommandFactory myCommandFactory;
	private UnbundlerFactory myUnbundlerFactory;
	private String[] myStringArr;
	private int myDex;
	private Node myDummyRoot;
	private Node myCurrent;
	private Set<String> myControlSet;
	private Map<String, String> myVarMap;
	private Map<String, String> myFuncMap;

	public Parser(CommandFactory cf) {
		myCommandFactory = cf;
		myUnbundlerFactory = new UnbundlerFactory();
		myControlSet = new HashSet<>(Arrays.asList(CONTROL_NAMES));
	}

	public Iterator<Commandable> parse(String s) {
		return parse(s.split(" "));
	}
	
	public Iterator<Commandable> parse(String[] arr) {
		myStringArr = arr;
		myDex = 0;
		myDummyRoot = new Node(myCommandFactory.createCommand("null"));
		myCurrent = myDummyRoot;
		return iterator();
	}

	@Override
	public Iterator<Commandable> iterator() {
		return new Iterator<Commandable>() {

			@Override
			public boolean hasNext() {
				return myCurrent.getParent() != myDummyRoot || myDex < myStringArr.length;
			}

			@Override
			public Commandable next() {
				if (myCurrent.isReady() && myCurrent != myDummyRoot) { // means that we just executed a command we need the result of
					Double ans = myCurrent.getAns();
					myCurrent.getParent().inject(ans);
					myCurrent = myCurrent.getParent();
					return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
				} else {
					String next = myStringArr[myDex]; 
					if (myControlSet.contains(next)) {
						Unbundler unbundler = myUnbundlerFactory.createUnbundler(next);
						// need unbundled to rip out the commandflow stuff and return an array of Strings / string most likely
						String[] unbundled_arr = unbundler.unbundle(myStringArr, myDex);
						Iterator<Commandable> i = new Parser(myCommandFactory).parse(unbundled_arr);
						Double ans;
						while (i.hasNext()) {
							Commandable c = i.next();
							System.out.println("in parser: " + c.execute());
							ans = c.getAns();
						}
						myCurrent.getParent().inject(ans);
						myCurrent = myCurrent.getParent();
						return myCurrent.isReady() ? myCurrent.getCommandable() : findNext();
					} else {
						createNode(next);
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
			String curr = myStringArr[myDex];
			if (isArgument(curr)) {
				myCurrent.inject(Double.parseDouble(curr));
			} else if (isCommand(curr)) {
//				if (myControlSet.contains(curr)) {
//					Unbundler unbundler = myUnbundlerFactory.createUnbundler(curr);
//					unbundler.unbundle(myStringArr, myDex);
//				} else {
					createNode(curr);
//				}	
			}
			myDex += 1;
		}
		System.out.println("exited");
		return myCurrent.getCommandable();
	}

	private void createNode(String command) {
		Commandable c = myCommandFactory.createCommand(command);
		Node new_node = new Node(c);
		new_node.setParent(myCurrent);
		myCurrent.addToChildren(new_node);
		myCurrent = new_node;
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
