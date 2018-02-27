package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Commandable;

public class Node {
	
	private List<Node> myChildren = new ArrayList<Node>();
	private Commandable myCommandable;
	private Node myParent;
	
	public Node(Commandable c) {
		myCommandable = c;
	}
	
	public boolean isReady() {
		return myCommandable.isReady();
	}
	
	public Node getParent() {
		return myParent;
	}
	
	public void inject(Double arg) {
		myCommandable.inject(arg);
	}
	
	public Double getAns() {
		return myCommandable.getAns();
	}
	
	public void setParent(Node node) {
		myParent = node;
	}
	
	public void addToChildren(Node node) {
		myChildren.add(node);
	}
	
	public Commandable getCommandable() {
		return myCommandable;
	}
}
