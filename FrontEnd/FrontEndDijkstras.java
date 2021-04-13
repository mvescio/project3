import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class FrontEndDijkstras {

	/**
	   * @param args
	   * @throws FileNotFoundException throws FileNotFoundException while implementing BackendInterface
	   * @throws IOException           throws IONotFoundException while implementing BackendInterface
	   * @throws DataFormatException   throws DataNotFoundException while implementing BackendInterface
	   */
	  public static void main(String[] args)
	      throws FileNotFoundException, IOException, DataFormatException {
		  
		
		/*BackendInterface graph = new BackendInterface();*/
	    CS400Graph<String> graph = new CS400Graph<String>();
	    CampusDataReader reader = new CampusDataReader();
	    List<Destination> data = reader.readDataSet();
	    
	    for (int i=10; i<data.size(); i++) {
	      if (!graph.containsVertex(data.get(i).getName()) && !graph.containsVertex(data.get(i).getDest())) {
	        graph.insertVertex(data.get(i).getName());
	        graph.insertVertex(data.get(i).getDest());
	        graph.insertEdge(data.get(i).getName(), data.get(i).getDest(), data.get(i).getCost());
	      } else if (!graph.containsVertex(data.get(i).getName()) && graph.containsVertex(data.get(i).getDest())) {
	        graph.insertVertex(data.get(i).getName());
	        graph.insertEdge(data.get(i).getName(), data.get(i).getDest(), data.get(i).getCost());
	      } else if (graph.containsVertex(data.get(i).getName()) && !graph.containsVertex(data.get(i).getDest())) {
	        graph.insertVertex(data.get(i).getDest());
            graph.insertEdge(data.get(i).getName(), data.get(i).getDest(), data.get(i).getCost());
	      } else {
	        graph.insertEdge(data.get(i).getName(), data.get(i).getDest(), data.get(i).getCost());
	      }
	    }
	    
	    String[] options = new String[graph.vertices.size() + 1];
	    options[0] = null;
	    options[1] = "Camp Randall";
	    options[2] = "Dejope Hall";
	    options[3] = "Union South";
	    options[4] = "Waters Hall";
	    options[5] = "Van Vleck Hall";
	    options[6] = "Memorial Union";
	    options[7] = "Humanities";
	    options[8] = "Witte Hall";

	    // Welcome message to the user
	    System.out.println("Welcome to the UW Campus Map!");
	    System.out.println();
	    
	    //Begins with choosing start and finish locations
	    chooseBoth(graph, options);

	  }
	  
	  public static void chooseBoth(CS400Graph<String> graph, String[] options) {
		  
		  System.out.println("Please choose one of the following as your starting point: ");
		    
		    
		    for (int i = 1; i < options.length; i++) {
		    	System.out.println("[" + i + "]: " + options[i]);
		    }
		    
		    Scanner sc = new Scanner(System.in);
		    int choiceOfStart = sc.nextInt();
		    String start = "";
		    
		    while (choiceOfStart < 1 || choiceOfStart > 8) {
		    	System.out.println("Please select a valid starting location.");
		    	choiceOfStart = sc.nextInt();
		    }
		    
		    start = options[choiceOfStart];
		    
		    System.out.println();
		    System.out.println(start + " is your starting location.");
		    System.out.println();
		    
		    System.out.println("Please choose one of the following as your ending point: ");
		    
		    for (int i = 1; i < options.length; i++) {
		    	System.out.println("[" + i + "]: " + options[i]);
		    }
		    
		    int choiceOfEnd = sc.nextInt();
		    String end = "";
		    
		    while (choiceOfEnd < 1 || choiceOfEnd > 8) {
		    	System.out.println("Please select a valid ending location.");
		    	choiceOfEnd = sc.nextInt();
		    }
		    
		    end = options[choiceOfEnd];
		    
		    System.out.println();
		    System.out.println(end + " is your ending location.");
		    System.out.println();
		    
		    System.out.println("Shortest path between " + start + " and " + end + ": ");
		    System.out.println(graph.shortestPath(start, end));
		    //System.out.println(graph.printRoute());
		    System.out.println("Total distance: " + graph.getPathCost(start, end) + " yards.");
		    //System.out.println("Nearby buildings: " + graph.printAdjacentLocations(start, end));
		    
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
		  for (int i = 1; i < options.length; i++) {
		    	System.out.println("[" + i + "]: " + options[i]);
		    }
		    
		  int choiceOfStart = sc.nextInt();
		    String start = "";
		    
		    while (choiceOfStart < 1 || choiceOfStart > 8) {
		    	System.out.println("Please select a valid starting location.");
		    	choiceOfStart = sc.nextInt();
		    }
		    
		    start = options[choiceOfStart];
		  
		  System.out.println(start + " is your new starting location.");
		    System.out.println();
		  
		  System.out.println("Shortest path between " + start + " and " + end + ": ");
		    System.out.println(graph.shortestPath(start, end));
		  //System.out.println(graph.printRoute());
		    System.out.println("Total distance: " + graph.getPathCost(start, end) + " yards.");
		    //System.out.println("Nearby buildings: " + graph.printAdjacentLocations(start, end));
		    
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
		  for (int i = 1; i < options.length; i++) {
		    	System.out.println("[" + i + "]: " + options[i]);
		    }
		    
		  int choiceOfEnd = sc.nextInt();
		    String end = "";
		    
		    while (choiceOfEnd < 1 || choiceOfEnd > 8) {
		    	System.out.println("Please select a valid ending location.");
		    	choiceOfEnd = sc.nextInt();
		    }
		    
		    end = options[choiceOfEnd];
		  
		  System.out.println(end + " is your new ending location.");
		    System.out.println();
		  
		  System.out.println("Shortest path between " + start + " and " + end + ": ");
		    System.out.println(graph.shortestPath(start, end));
		    //System.out.println(graph.printRoute());
		    System.out.println("Total distance: " + graph.getPathCost(start, end) + " yards.");
		    //System.out.println("Nearby buildings: " + graph.printAdjacentLocations(start, end));
		    
		    
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
