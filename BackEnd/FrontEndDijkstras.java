// --== CS400 File Header Information ==--
// Name: Jonathon Byrnes
// Email: jdbyrnes@gmail.com
// Team: Blue
// Role: Front End
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: null

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

//This class instantiates a graph of vertices and edges, and allows a user to find the shortest path between them.
public class FrontEndDijkstras {

	/**
	 * @param args
	 * @throws FileNotFoundException throws FileNotFoundException while implementing
	 *                               CampusDataReader
	 * @throws IOException           throws IONotFoundException while implementing
	 *                               CampusDataReader
	 * @throws DataFormatException   throws DataNotFoundException while implementing
	 *                               CampusDataReader
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, DataFormatException {
		
		//Creates the graph
		BackEndDijkstra<String> graph = new BackEndDijkstra<String>();

		
		CampusDataReader reader = new CampusDataReader();
		try {
			reader.readDataSet();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException f) {
			System.out.println(f);
		} catch (DataFormatException g) {
			System.out.println(g);
		}

		
		//Creates vertices on the graph
		for (int i = 0; i < reader.vertices.size(); i++) {
			graph.insertVertex(reader.vertices.get(i));
		}
		
		//Creates edges in the graph 
		for (int i = 1; i < reader.edges.length; i++) {
			Integer cost = Integer.valueOf((String) reader.edges[i][2]);
			String startV = (String) reader.edges[i][0];
			String endV = (String) reader.edges[i][1];
			graph.insertEdge(startV, endV, cost);
		}

		//list of locations for the user to choose from
		String[] options = new String[reader.vertices.size()];
		for (int i = 0; i < reader.vertices.size(); i++) {
			options[i] = reader.vertices.get(i);
		}


		// Welcome message to the user
		System.out.println("Welcome to the UW Campus Map!");
		System.out.println();

		// Begins with choosing start and finish locations
		chooseBoth(graph, options);

	}

	/*
	 * chooseBoth allows the user to choose both a start and end location to find the shortest path.
	 * @param graph   the graph of vertices and edges
	 * @param options  an array of the locations on the graph
	 */
	public static void chooseBoth(BackEndDijkstra<String> graph, String[] options) {

		System.out.println("Please choose one of the following as your starting point: ");

		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		Scanner sc = new Scanner(System.in);
		int choiceOfStart = sc.nextInt();
		String start = "";

		//Only allows for input between 1 and 9
		while (choiceOfStart < 1 || choiceOfStart > 9) {
			System.out.println("Please select a valid starting location.");
			choiceOfStart = sc.nextInt();
		}

		start = options[choiceOfStart - 1];

		System.out.println();
		System.out.println(start + " is your starting location.");
		System.out.println();

		System.out.println("Please choose one of the following as your ending point: ");

		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		int choiceOfEnd = sc.nextInt();
		String end = "";

		//Only allows for input between 1 and 9
		while (choiceOfEnd < 1 || choiceOfEnd > 9) {
			System.out.println("Please select a valid ending location.");
			choiceOfEnd = sc.nextInt();
		}

		end = options[choiceOfEnd - 1];

		System.out.println();
		System.out.println(end + " is your ending location.");
		System.out.println();

		System.out.println("Shortest path between " + start + " and " + end + ": ");
		System.out.println(graph.shortestPath(start, end));
		System.out.println("Total time: " + graph.getPathCost(start, end) + " minutes.");

		System.out.println("Would you like to print buildings adjacent to your ending location? [Y]/[N]");

		String p = sc.next();
		//Makes sure the input is either Y or N, excluding capitalization
		while (!p.equals("Y") && !p.equals("y") && !p.equals("N") && !p.equals("n")) {
			System.out.println("Please enter Y for yes, or N for no.");
			p = sc.next();
		}

		if (p.equals("y") || p.equals("Y")) {
			graph.adjacentLocations(start, end);
		}

		System.out.println();

		System.out.println();
		
		//Gives the user options as for what to do next
		System.out.println("Please select an option: ");
		System.out.println("[1] Start Over.");
		System.out.println("[2] Change starting point.");
		System.out.println("[3] Change ending point.");
		System.out.println("[4] Close program.");

		int choice = sc.nextInt();

		//Ensures the choice is between 1 and 4
		while (choice < 1 || choice > 4) {
			System.out.println("Please enter a valid option.");
			choice = sc.nextInt();
		}

		if (choice == 1) {
			chooseBoth(graph, options);
			sc.close();
			return;
		}

		if (choice == 2) {
			changeStart(graph, end, options);
			sc.close();
			return;
		}

		if (choice == 3) {
			changeEnd(graph, start, options);
			sc.close();
			return;
		}

		if (choice == 4) {
			System.out.println("Goodbye!");
			sc.close();
			return;
		}

		sc.close();
	}

	/*
	 * changeStart changes the starting point of the path only
	 * @param graph  the graph of vertices and edges
	 * @param end  the ending location from the previous shortest path
	 * @param options   the list of locations
	 */
	public static void changeStart(BackEndDijkstra<String> graph, String end, String[] options) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose a new starting location.");
		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		int choiceOfStart = sc.nextInt();
		String start = "";

		//Ensures the user chooses between 1 and 9
		while (choiceOfStart < 1 || choiceOfStart > 9) {
			System.out.println("Please select a valid starting location.");
			choiceOfStart = sc.nextInt();
		}

		start = options[choiceOfStart - 1]; //Choice of new starting location

		System.out.println(start + " is your new starting location.");
		System.out.println();

		System.out.println("Shortest path between " + start + " and " + end + ": ");
		System.out.println(graph.shortestPath(start, end));
		System.out.println("Total time: " + graph.getPathCost(start, end) + " minutes.");

		System.out.println("Would you like to print buildings adjacent to your ending location? [Y]/[N]");

		String p = sc.next();
		
		//Ensures the user inputs Y or N, regardless of capitalization
		while (!p.equals("Y") && !p.equals("y") && !p.equals("N") && !p.equals("n")) {
			System.out.println("Please enter Y for yes, or N for no.");
			p = sc.next();
		}

		if (p.equals("y") || p.equals("Y")) {
			graph.adjacentLocations(start, end);
		}

		System.out.println();
		//Prints options for the user
		System.out.println("Please select an option: ");
		System.out.println("[1] Start Over.");
		System.out.println("[2] Change starting point.");
		System.out.println("[3] Change ending point.");
		System.out.println("[4] Close program.");

		int choice = sc.nextInt();

		//Ensures the user chooses between 1 and 4
		while (choice < 1 || choice > 4) {
			System.out.println("Please enter a valid option.");
			choice = sc.nextInt();
		}

		if (choice == 1) {
			chooseBoth(graph, options);
			sc.close();
			return;
		}

		if (choice == 2) {
			changeStart(graph, end, options);
			sc.close();
			return;
		}

		if (choice == 3) {
			changeEnd(graph, start, options);
			sc.close();
			return;
		}

		if (choice == 4) {
			System.out.println("Goodbye!");
			sc.close();
			return;
		}

		sc.close();
	}

	/*
	 * changeStart changes the ending point of the path only
	 * @param graph  the graph of vertices and edges
	 * @param start  the starting location from the previous shortest path
	 * @param options   the list of locations
	 */
	public static void changeEnd(BackEndDijkstra<String> graph, String start, String[] options) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose a new ending location.");
		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		int choiceOfEnd = sc.nextInt();
		String end = "";

		//Ensures choice is between 1 and 9
		while (choiceOfEnd < 1 || choiceOfEnd > 9) {
			System.out.println("Please select a valid ending location.");
			choiceOfEnd = sc.nextInt();
		}

		end = options[choiceOfEnd - 1]; //Choice of new ending location

		System.out.println(end + " is your new ending location.");
		System.out.println();

		System.out.println("Shortest path between " + start + " and " + end + ": ");
		System.out.println(graph.shortestPath(start, end));
		System.out.println("Total time: " + graph.getPathCost(start, end) + " minutes.");

		System.out.println("Would you like to print buildings adjacent to your ending location? [Y]/[N]");

		String p = sc.next();
		
		//Ensures the user chooses Y or N, regardless of capitalization
		while (!p.equals("Y") && !p.equals("y") && !p.equals("N") && !p.equals("n")) {
			System.out.println("Please enter Y for yes, or N for no.");
			p = sc.next();
		}

		if (p.equals("y") || p.equals("Y")) {
			graph.adjacentLocations(start, end);
		}

		System.out.println();
		//Prints options for the user
		System.out.println("Please select an option: ");
		System.out.println("[1] Start Over.");
		System.out.println("[2] Change starting point.");
		System.out.println("[3] Change ending point.");
		System.out.println("[4] Close program.");

		int choice = sc.nextInt();

		//Ensures choice is between 1 and 4
		while (choice < 1 || choice > 4) {
			System.out.println("Please enter a valid option.");
			choice = sc.nextInt();
		}

		if (choice == 1) {
			chooseBoth(graph, options);
			sc.close();
			return;
		}

		if (choice == 2) {
			changeStart(graph, end, options);
			sc.close();
			return;
		}

		if (choice == 3) {
			changeEnd(graph, start, options);
			sc.close();
			return;
		}

		if (choice == 4) {
			System.out.println("Goodbye!");
			sc.close();
			return;
		}

		sc.close();
	}
}
