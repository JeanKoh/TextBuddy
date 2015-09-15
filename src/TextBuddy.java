import java.util.*;
import java.io.*;
/**
 * This class is used to store and retrieve statements from a file
 * It is assumed that statement numbering will be dynamic
 * File will only be saved after the program is exited
 * File will be overwritten if there is an existing file with the same name.
 * The comment format is given by the example interaction below:

		Welcome to TextBuddy. mytextfile.txt is ready for use
		command: add little brown fox
		added to mytextfile.txt: "little brown fox"
		command: display
		1. little brown fox
		command: add jumped over the moon
		added to mytextfile.txt: "jumped over the moon"
		command: display
		1. little brown fox
		2. jumped over the moon
		command: delete 2
		deleted from mytextfile.txt: "jumped over the moon"
		command: display
		1. little brown fox
		command: clear
		all content deleted from mytextfile.txt
		command: display
		mytextfile.txt is empty
		command: exit
 * 
 * @author Koh Ling Ling Jean
 *
 */

public class TextBuddy{

	private static String FILE_NAME = "";
	
	//List of possible messages
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String MESSAGE_ADD = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private static final String MESSAGE_CLEAR = "all content deleted from %1$s";
	
	//List of possible errors
	private static final String ERROR_NO_COMMAND = "no such command";
	private static final String ERROR_NO_SUCH_INDEX = "no such index";
	private static final String ERROR_NO_SEARCH_INDEX = "no such index from search display";
	private static final String ERROR_NO_MATCH = "no keywords input";
	private static final String ERROR_NO_MATCH_FOUND = "no match";

	//This arraylist will be used to store data for the file
	private static ArrayList<String> contents = new ArrayList<String>();
	private static ArrayList<String> searchedContents = new ArrayList<String>();

	public static void main(String[] args) {
		printWelcomeMessage(args);
		try {
			File file = createFile();
			readInput(file);
		} catch (IOException error){
			error.printStackTrace();
		}
	}

	/**
	 * this operation outputs the welcome message to user
	 * 
	 * @param name	name of new file to be created
	 */
	public static void printWelcomeMessage (String[] name){
		String fileName = Arrays.toString(name);
		FILE_NAME= fileName.substring (1, fileName.length()-1);
		System.out.println (String.format(MESSAGE_WELCOME, FILE_NAME ));
	}
	
	/**
	 * this operation creates a new file object input name
	 * 
	 * @return	file created 
	 * @throws IOException when error occurs during creating new file
	 */
	public static File createFile()throws IOException {
		File file = new File(FILE_NAME);
		if (!file.exists()){
			file.createNewFile();
		}
		return file;
	}
	
	
	/**
	 * This operation determines which command to run base on user input
	 * 
	 * @param file is the newly created file named by the user
	 */
	public static void readInput(File file) {
		Scanner sc = new Scanner(System.in);
		System.out.print("command: ");

		while (!sc.hasNext("exit")){
			String command = sc.next();
			switch(command){
				case "add":
					add(sc.nextLine());
					break; 
				case "delete":
					delete(sc.nextInt());
					break;
				case "display":
					display();
					break;
				case "clear":
					clear();
					break;
				case "search":
					displaySearch(sc.nextLine());
					break;
				case "sort":
					sortByAlpha();
					break;
				default:
					System.out.println(ERROR_NO_COMMAND);
			}
			System.out.print("command: ");
		}
		try {
			exitAndSave(file);
		} catch (IOException error) {
			System.out.println(error);
		}
		sc.close();
	}

	/**
	 * this operation adds the string into an array list
	 * 
	 * @param statement
	 */
	public static boolean add(String statement) {
		if (statement == null){
			return false;
		} else {
			statement = statement.substring(1,statement.length());
			contents.add(statement);
			System.out.println(String.format(MESSAGE_ADD, FILE_NAME, statement));
			return true;
		}
	}
	
	/**
	 * this operation deletes the statement corresponding to the given index.
	 * If search function was used prior, it will delete the index from search list
	 * 
	 * @param num index to be deleted
	 */
	public static void delete(int num) {
		if (!searchedContents.isEmpty()) {
			String toDelete = searchFromList(num);
			if (toDelete!=null){ 
				deleteUsingString(toDelete);
			}
		} else {
			deleteUsingIndex(num);
		}
	}
	/**
	 * This operation returns the corresponding string from the searched list.
	 * 
	 * @param num index to be deleted
	 * @return
	 */
	public static String searchFromList(int num){
		if (num>searchedContents.size()){
			System.out.println(ERROR_NO_SEARCH_INDEX);
			
		} else {
			String foundSearch = searchedContents.get(num-1);
			searchedContents.clear();
			return (foundSearch);
		}
		return  null;
	}
	
	/**
	 * This operation delete from the main contents using a String object
	 * 
	 * @param toDelete
	 */
	public static void deleteUsingString(String toDelete){
		System.out.println(String.format(MESSAGE_DELETED,FILE_NAME,toDelete));
		contents.remove(toDelete);
	}
	
	/**
	 * This operation delete from the main contents using the index
	 * 
	 * @param num
	 */
	public static void deleteUsingIndex(int num){
		if (num>contents.size()) {
			System.out.println(ERROR_NO_SUCH_INDEX);
		}
		System.out.println(String.format(MESSAGE_DELETED,FILE_NAME,contents.get(num-1)));
		contents.remove(num-1);
	}

	public static ArrayList<String> display() {
		ArrayList<String> ans = new ArrayList<String>(); //for testing purpose in jUnit
		if (contents.isEmpty()){
			System.out.println(String.format(MESSAGE_EMPTY,FILE_NAME));
		} else {
			for (int i = 0; i<contents.size();i++) {
				ans.add((i+1)+". "+contents.get(i));
				System.out.println((i+1)+". "+contents.get(i));
			}
		}
		return ans;
	}
	
	public static void clear() {
		contents.clear();
		System.out.println(String.format(MESSAGE_CLEAR,FILE_NAME));
	}
	
	/**
	 * This operation saves the data into the file when user exits the program
	 * 
	 * @param file	file to be written into
	 * @throws IOException is thrown when errors occurs during writing
	 */
	public static void exitAndSave(File file)throws IOException{
		FileWriter writer = new FileWriter(file);
		BufferedWriter bWriter = new BufferedWriter(writer);
		for (int i =0;i <contents.size();i++) {
			bWriter.write((i+1)+". "+contents.get(i)+"\n");
		}
		bWriter.close();
	}

	/**
	 * Sorts the current data in alphabetical
	 * 
	 * @return returns if method is successful
	 */
	public static boolean sortByAlpha() {
		if (contents.isEmpty()) {
			return false;
		} else {
			Collections.sort(contents);
			return true;
		}
	}
	
	/**
	 *this operation display the list of string matching the search input 
	 * 
	 * @param keywords input to be matched
	 */
	public static void displaySearch(String keywords){
		if (keywords == null){
			System.out.println(ERROR_NO_MATCH);
		}
		keywords = keywords.substring(1,keywords.length());
		ArrayList<String> results = searchKeyword(keywords);
		if (results == null){
			System.out.println(ERROR_NO_MATCH_FOUND);
		} else {
			for (int i =0; i<results.size();i++) {
				System.out.println((i+1)+". "+ results.get(i));
			}
		}
	}
	/**
	 * this operation search for keywords in the main content.
	 * 
	 * @param key words to look out for.
	 * @return list containing search result
	 */
	public static ArrayList<String> searchKeyword(String key){
		ArrayList<String> searchedResults = new ArrayList<String>();
		if (contents.isEmpty() || key == null){
			return null;
		} else {
			for (int i = 0;i<contents.size();i++) {
				if (contents.get(i).contains(key)){
					searchedResults.add(contents.get(i));
				}
			}
			if (searchedResults.isEmpty()){
				return null;
			} else {
				searchedContents = searchedResults;
				return searchedResults;
			}
		}
	}
}
