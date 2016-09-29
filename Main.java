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

//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;

import java.io.*;

public class Main {
	
	private static Set<String> dictionary;
	private static String start, end;
	private static ArrayList<String> checked;
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		// TODO methods to read in words, output ladder
		ArrayList<String> empty = new ArrayList<String>();
		printLadder(empty);
		System.out.println("Here's a test, type something in:");
		ArrayList<String> toParse = parse(kb);
		start = toParse.get(0);
		end = toParse.get(1);
		//ArrayList<String> test = getWordLadderBFS(toParse.get(0), toParse.get(1));
		ArrayList<String> DFS = getWordLadderDFS(toParse.get(0), toParse.get(1));
		//printLadder(test);
		printLadder(DFS);
		//Use ArrayList head and getLast for the testing;
		
		//Result result = JUnitCore.runClasses(TestJUnit.class);
		//System.out.println(result.wasSuccessful());
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dictionary = makeDictionary();
		checked = new ArrayList<String>();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String input = keyboard.nextLine();
		ArrayList<String> answer = new ArrayList<String>();
		input = input.toLowerCase().trim();
		if(input.equals("/quit"))
		{
		System.exit(0);
		return answer;
		}
		else
		{
			int spaceIndex = input.indexOf(' ');
			answer.add(input.substring(0, spaceIndex).toLowerCase());
			answer.add(input.substring(spaceIndex+1, input.length()).toLowerCase());
			return answer;
		}
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		ArrayList<String> answer = new ArrayList<String>();
		if(start == null || end == null || start.length() != end.length())
			return answer;
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		
		checked.add(start);
		remake();
		if(start.equals(end)){
			answer.add(start);
			return answer;
		}
		
		/*
		 * set up tree of touching words
		 * 
		 */
		
		Node parent = new Node();
		parent.word = start;
		String check;
		for(int j = 0; j < 5; j++){
			check = parent.word.substring(0,j) + end.charAt(j) + parent.word.substring(j+1,parent.word.length());
			if(check.equals(start)){			
			}
			else if(!beenChecked(check) && inDictionary(check) ){
				Node child = new Node(parent);
				parent.children.add(child);
				parent.children.get(parent.children.size()-1).word = check;
			}
		}
		
		for(int j = 0; j < 5; j++){
			for(char c = 'a'; c <= 'z'; c++){
				check = parent.word.substring(0,j) + c + parent.word.substring(j+1,parent.word.length());
				if(check.equals(start)){
					
				}
				else if(!beenChecked(check) && inDictionary(check) ){
					Node child = new Node(parent);
					parent.children.add(child);
					parent.children.get(parent.children.size()-1).word = check;
				}
			}
		}
		
		for(int i = 0; i < parent.children.size(); i++){
			
			//	RECURSIVE CALL
			ArrayList<String> recAnswer = getWordLadderDFS(parent.children.get(i).word, end);
			
			
			if(recAnswer.size() == 0){
				answer = recAnswer;
			}
			else{
				answer.add(start);
				for(int j = 0; j < recAnswer.size(); j++){
					answer.add(recAnswer.get(j));		
				}
				return answer;
			}
			
		}
		
		return answer; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		remake();
		ArrayList<String> answer = new ArrayList<String>();
		if(start == null || end == null || start.length() != end.length())
			return answer;
		start = start.toUpperCase();
		end = end.toUpperCase();
		List<String> paths = new LinkedList<String>();
		paths.add(start);
		Queue<WordLadder> queue = new LinkedList<WordLadder>();
		queue.add(new WordLadder(paths, start));
		
		dictionary.remove(start);//remember to readd the start word before return the array list
		while(!queue.isEmpty() && !queue.peek().equals(end))
		{
			WordLadder temp = queue.remove();
			
			if(end.equals(temp.getLastWord())){
				List<String> toUse = temp.getPath();
				int size = toUse.size();
				String[] preAnswer = toUse.toArray(new String[size]);
				for(int i = 0; i < preAnswer.length; i ++)
					answer.add(preAnswer[i]);
				return answer;
			}
			Iterator<String> it = dictionary.iterator();
			while(it.hasNext())
			{
				String dictTemp = it.next();
				if(differByOne(dictTemp, temp.getLastWord()))
				{
					List<String> list = new LinkedList<String>(temp.getPath());
					list.add(dictTemp);
					
					queue.add(new WordLadder(list, dictTemp));
					it.remove();
				}
			}
		}
		if(!queue.isEmpty())
		{
			WordLadder last = queue.peek();
			List<String> toUse = last.getPath();
			int size = toUse.size();
			String[] preAnswer = toUse.toArray(new String[size]);
			for(int i = 0; i < preAnswer.length; i ++)
				answer.add(preAnswer[i]);
			return answer;
		}
		return answer;
	}
		
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		int rung = ladder.size();
		if(rung == 0)
		{	if(start == null || end == null){
				System.out.println("no ladder exists at all.");
				return;
			}
			else{
				System.out.println("no ladder exists between " + start + " and " + end);
				return;
			}
		}
			else if(rung == 2&&!differByOne(ladder.get(0),ladder.get(1)))
		{
			System.out.println("no ladder exists between " + ladder.get(0).toLowerCase() + " and " + ladder.get(1).toLowerCase());
		}
		else{
		System.out.println("a " + (rung -2) + "" + "-rung ladder exists between " +ladder.get(0).toLowerCase() + " and " + ladder.get(rung - 1).toLowerCase());
		for(int i = 0; i < rung; i++)
			{			
				String print = ladder.get(i);
				System.out.println(print.toLowerCase());
			}
		}
	}
	private static boolean inDictionary(String s){
		
		Iterator<String> dict = dictionary.iterator();
		while(dict.hasNext()){
			String check = dict.next().toLowerCase();
			if(s.equals(check)){
				return true;
			}
			
		}
		return false;
	}
	
	private static boolean beenChecked(String s){
		return checked.contains(s);
	}
	
	private static boolean differByOne(String word1, String word2)
	{
		if (word1.length() != word2.length())
		{
			return false;
		}
		int diffCount = 0;
		for(int i = 0; i < word1.length(); i++)
		{
			if (word1.charAt(i) != word2.charAt(i))
			{
				diffCount++;
			}
		}
		return (diffCount == 1);
	}
	
	private static void remake()
	{
		dictionary = makeDictionary();
	}
	// TODO
	// Other private static methods here
}
