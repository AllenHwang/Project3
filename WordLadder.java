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

