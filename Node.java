package assignment3;

import java.util.*;

public class Node {
	String word;
	Node parent;
	ArrayList<Node>	children;
	int level;
	
	Node(){
		children = new ArrayList<Node>();
	}
	
	Node(Node parentNode){
		parent = parentNode;
		children = new ArrayList<Node>();
	}
	
}
