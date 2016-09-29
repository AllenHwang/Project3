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
 * Git URL: https://github.com/HorizonStrider/Project3
 * Fall 2016
 */

package assignment3;
import java.util.*;

public class WordLadder {
	 private List<String> potentialLadder; //A given "ladder"
	 private String lastWord;  

	 public WordLadder(List<String> path) {
	  this.potentialLadder=path;
	 }
	 
	 public WordLadder(List<String> path, String lastWord) {
	  this.potentialLadder=path;

	  this.lastWord=lastWord;
	 }
	 public List<String> getPath() {
	  return potentialLadder;
	 }
	 public String getLastWord() {
	  return lastWord;
	 }
	 
	 public void setPath(List<String> path) {
	  this.potentialLadder = path;
	 }

}

