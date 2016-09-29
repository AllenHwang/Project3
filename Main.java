/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Allen Hwang
 * ah45755
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	private static Set<String> dictionary;
	private static String start, end;
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
		System.out.println("Here's a test, type something in:");
		ArrayList<String> toParse = parse(kb);
		start = toParse.get(0).toLowerCase();
		end = toParse.get(1).toLowerCase();
		ArrayList<String> test = getWordLadderBFS(toParse.get(0), toParse.get(1));
		printLadder(test);
		//Use ArrayList head and getLast for the testing;
		
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dictionary = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String input = keyboard.nextLine();
		ArrayList<String> answer = new ArrayList<String>();
		input = input.toLowerCase();
		if(input.equals("/quit"))
		{
		System.exit(0);
		return answer;
		}
		else
		{
			int spaceIndex = input.indexOf(' ');
			answer.add(input.substring(0, spaceIndex).toUpperCase());
			answer.add(input.substring(spaceIndex+1, input.length()).toUpperCase());
			return answer;
		}
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		ArrayList<String> answer = new ArrayList<String>();
		if(start == null || end == null || start.length() != end.length()||start.equals(end))
			return answer;
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		ArrayList<String> answer = new ArrayList<String>();
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
				remake();
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
			remake();
			return answer;
		}
		return answer;
	}
		
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
			//infile = new Scanner (new File("short_dict.txt"));
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
		boolean status = differByOne(ladder.get(0),ladder.get(1));
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
			else if(rung == 2&&!status)
		{
			System.out.println("no ladder exists between " + ladder.get(0).toLowerCase() + " and " + ladder.get(1).toLowerCase());
		}
		else{
		System.out.println("a " + (rung -2) + "" + "-rung ladder exists between " +ladder.get(0).toLowerCase() + " and " + ladder.get(rung - 1).toLowerCase());
		while(!ladder.isEmpty())
			{			
				String print = ladder.remove(0);
				System.out.println(print.toLowerCase());
			}
		}
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
