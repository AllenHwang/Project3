/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Allen Hwang
 * ah45755
 * 16445
 * Paris Kaman
 * pak679
 * 16445
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */

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
