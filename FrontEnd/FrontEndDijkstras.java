import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class FrontEndDijkstras {

	/**
	 * @param args
	 * @throws FileNotFoundException throws FileNotFoundException while implementing
	 *                               BackendInterface
	 * @throws IOException           throws IONotFoundException while implementing
	 *                               BackendInterface
	 * @throws DataFormatException   throws DataNotFoundException while implementing
	 *                               BackendInterface
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, DataFormatException {

		/* BackendInterface graph = new BackendInterface(); */
		CS400Graph<String> graph = new CS400Graph<String>();
		/*
		 * graph.insertVertex("Dejope Hall"); graph.insertVertex("Camp Randall");
		 * graph.insertVertex("Union South"); graph.insertVertex("Memorial Union");
		 * graph.insertVertex("Van Vleck Hall"); graph.insertVertex("Humanities");
		 * graph.insertVertex("Witte Hall"); graph.insertVertex("Waters Hall");
		 * 
		 * 
		 * 
		 * graph.insertEdge("Dejope Hall", "Camp Randall", 8);
		 * graph.insertEdge("Dejope Hall", "Union South", 7);
		 * graph.insertEdge("Dejope Hall", "Waters Hall", 3);
		 * 
		 * graph.insertEdge("Camp Randall", "Union South", 2);
		 * graph.insertEdge("Camp Randall", "Dejope Hall", 8);
		 * graph.insertEdge("Camp Randall", "Witte Hall", 8);
		 * 
		 * graph.insertEdge("Union South", "Dejope Hall", 7);
		 * graph.insertEdge("Union South", "Van Vleck Hall", 4);
		 * graph.insertEdge("Union South", "Witte Hall", 7);
		 * graph.insertEdge("Union South", "Camp Randall", 2);
		 * 
		 * graph.insertEdge("Waters Hall", "Dejope Hall", 8);
		 * graph.insertEdge("Waters Hall", "Memorial Union", 8);
		 * graph.insertEdge("Waters Hall", "Van Vleck Hall", 2);
		 * 
		 * graph.insertEdge("Van Vleck Hall", "Waters Hall", 2);
		 * graph.insertEdge("Van Vleck Hall", "Memorial Union", 7);
		 * graph.insertEdge("Van Vleck Hall", "Humanities", 6);
		 * graph.insertEdge("Van Vleck Hall", "Union South", 4);
		 * 
		 * graph.insertEdge("Memorial Union", "Waters Hall", 8);
		 * graph.insertEdge("Memorial Union", "Humanities", 2);
		 * graph.insertEdge("Memorial Union", "Van Vleck Hall", 7);
		 * 
		 * graph.insertEdge("Humanities", "Van Vleck Hall", 6);
		 * graph.insertEdge("Humanities", "Witte Hall", 4);
		 * graph.insertEdge("Humanities", "Memorial Union", 2);
		 * 
		 * graph.insertEdge("Witte Hall", "Humanities", 4);
		 * graph.insertEdge("Witte Hall", "Union South", 7);
		 * graph.insertEdge("Witte Hall", "Camp Randall", 8);
		 */

		CampusDataReader reader = new CampusDataReader();
		reader.readDataSet();
		for (int i = 0; i < reader.vertices.size(); i++) {
			graph.insertVertex(reader.vertices.get(i));
		}
		for (int i = 1; i < reader.edges.length; i++) {
			Integer cost = Integer.valueOf((String) reader.edges[i][2]);
			String startV = (String) reader.edges[i][0];
			String endV = (String) reader.edges[i][1];
			graph.insertEdge(startV, endV, cost);
		}

		String[] options = new String[reader.vertices.size()];
		for (int i = 0; i < reader.vertices.size(); i++) {
			options[i] = reader.vertices.get(i);
		}
		// String[] options = new String[graph.vertices.size() + 1];
		/*
		 * options[0] = null; options[1] = "Camp Randall"; options[2] = "Dejope Hall";
		 * options[3] = "Union South"; options[4] = "Waters Hall"; options[5] =
		 * "Van Vleck Hall"; options[6] = "Memorial Union"; options[7] = "Humanities";
		 * options[8] = "Witte Hall";
		 */

		// Welcome message to the user
		System.out.println("Welcome to the UW Campus Map!");
		System.out.println();

		// Begins with choosing start and finish locations
		chooseBoth(graph, options);

	}

	public static void chooseBoth(CS400Graph<String> graph, String[] options) {

		System.out.println("Please choose one of the following as your starting point: ");

		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		Scanner sc = new Scanner(System.in);
		int choiceOfStart = sc.nextInt();
		String start = "";

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
		// System.out.println(graph.printRoute());
		System.out.println("Total time: " + graph.getPathCost(start, end) + " minutes.");
		// graph.adjacentLocations(start, end);

		System.out.println("Would you like to print buildings adjacent to your ending location? [Y]/[N]");

		String p = sc.next();
		while (!p.equals("Y") && !p.equals("y") && !p.equals("N") && !p.equals("n")) {
			System.out.println("Please enter Y for yes, or N for no.");
			p = sc.next();
		}

		if (p.equals("y") || p.equals("Y")) {
			graph.adjacentLocations(start, end);
		}

		System.out.println();

		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println("[1] Start Over.");
		System.out.println("[2] Change starting point.");
		System.out.println("[3] Change ending point.");
		System.out.println("[4] Close program.");

		int choice = sc.nextInt();

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

	public static void changeStart(CS400Graph<String> graph, String end, String[] options) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose a new starting location.");
		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		int choiceOfStart = sc.nextInt();
		String start = "";

		while (choiceOfStart < 1 || choiceOfStart > 9) {
			System.out.println("Please select a valid starting location.");
			choiceOfStart = sc.nextInt();
		}

		start = options[choiceOfStart - 1];

		System.out.println(start + " is your new starting location.");
		System.out.println();

		System.out.println("Shortest path between " + start + " and " + end + ": ");
		System.out.println(graph.shortestPath(start, end));
		// System.out.println(graph.printRoute());
		System.out.println("Total time: " + graph.getPathCost(start, end) + " minutes.");
		// System.out.println("Nearby buildings: " + graph.printAdjacentLocations(start,
		// end));

		System.out.println("Would you like to print buildings adjacent to your ending location? [Y]/[N]");

		String p = sc.next();
		while (!p.equals("Y") && !p.equals("y") && !p.equals("N") && !p.equals("n")) {
			System.out.println("Please enter Y for yes, or N for no.");
			p = sc.next();
		}

		if (p.equals("y") || p.equals("Y")) {
			graph.adjacentLocations(start, end);
		}

		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println("[1] Start Over.");
		System.out.println("[2] Change starting point.");
		System.out.println("[3] Change ending point.");
		System.out.println("[4] Close program.");

		int choice = sc.nextInt();

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

	public static void changeEnd(CS400Graph<String> graph, String start, String[] options) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose a new ending location.");
		for (int i = 0; i < options.length; i++) {
			System.out.println("[" + (i + 1) + "]: " + options[i]);
		}

		int choiceOfEnd = sc.nextInt();
		String end = "";

		while (choiceOfEnd < 1 || choiceOfEnd > 9) {
			System.out.println("Please select a valid ending location.");
			choiceOfEnd = sc.nextInt();
		}

		end = options[choiceOfEnd - 1];

		System.out.println(end + " is your new ending location.");
		System.out.println();

		System.out.println("Shortest path between " + start + " and " + end + ": ");
		System.out.println(graph.shortestPath(start, end));
		// System.out.println(graph.printRoute());
		System.out.println("Total time: " + graph.getPathCost(start, end) + " minutes.");
		// System.out.println("Nearby buildings: " + graph.printAdjacentLocations(start,
		// end));
		
		System.out.println("Would you like to print buildings adjacent to your ending location? [Y]/[N]");
	    
	    String p = sc.next();
	    while (!p.equals("Y") && !p.equals("y") && !p.equals("N")&& !p.equals("n")) {
	    	System.out.println("Please enter Y for yes, or N for no.");
	    	p = sc.next();
	    }
	    
	    if (p.equals("y") || p.equals("Y")) {
	    	graph.adjacentLocations(start, end);
	    }

		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println("[1] Start Over.");
		System.out.println("[2] Change starting point.");
		System.out.println("[3] Change ending point.");
		System.out.println("[4] Close program.");

		int choice = sc.nextInt();

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
