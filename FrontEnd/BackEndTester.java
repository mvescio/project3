// --== CS400 File Header Information ==--
// Name: Anubhav Kumaria
// Email: kumaria@wisc.edu
// Team: GG blue
// Role: BackEnd Developer
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;

/**
 * This tester class tests the implementation of the BackEnd class of the Dijkstra algorith group
 * project.
 */

public class BackEndTester {


  private BackEndDijkstra<String> graph;


  /**
   * Instantiate graph to test the validity of the BackEnd methods.
   */
  @BeforeEach
  public void createGraph() {
    graph = new BackEndDijkstra<>();

    // inserts multiple locations (vertices) for flight paths both international and within the
    // continental United States.
    graph.insertVertex("Madison");
    graph.insertVertex("Chicago");
    graph.insertVertex("Los Angeles");
    graph.insertVertex("Singapore");
    graph.insertVertex("Doha");
    graph.insertVertex("New York");
    graph.insertVertex("Philadelphia");
    graph.insertVertex("Minneapolis");

    // insert flight paths (edges) between the locations above.
    graph.insertEdge("Madison", "Chicago", 1);
    graph.insertEdge("Chicago", "Madison", 1);
    graph.insertEdge("Madison", "Minneapolis", 2);
    graph.insertEdge("Minneapolis", "Madison", 2);
    graph.insertEdge("Madison", "Los Angeles", 5);
    graph.insertEdge("Los Angeles", "Madison", 5);
    graph.insertEdge("Los Angeles", "Singapore", 22);
    graph.insertEdge("Singapore", "Los Angeles", 22);
    graph.insertEdge("Doha", "Singapore", 8);
    graph.insertEdge("Singapore", "Doha", 8);
    graph.insertEdge("Chicago", "Doha", 14);
    graph.insertEdge("Doha", "Chicago", 14);
    graph.insertEdge("Chicago", "Philadelphia", 5);
    graph.insertEdge("Philadelphia", "Chicago", 5);
    graph.insertEdge("Chicago", "New York", 6);
    graph.insertEdge("New York", "Chicago", 6);
    graph.insertEdge("Minneapolis", "New York", 7);
    graph.insertEdge("New York", "Minneapolis", 7);
    graph.insertEdge("New York", "Singapore", 22);
    graph.insertEdge("Singapore", "New York", 22);
    graph.insertEdge("New York", "Philadelphia", 1);
    graph.insertEdge("Philadelphia", "New York", 1);

  }


  /**
   * This simple test checks whether the shortest path returned is correct or incorrect.
   */
  @Test
  public void TestShortestPath() {

    List<String> msn2sin = Arrays.asList("Madison", "Chicago", "Doha", "Singapore");

    assertEquals(graph.shortestPath("Madison", "Singapore"), msn2sin);

    List<String> doh2nyc = Arrays.asList("Doha", "Chicago", "New York");

    assertEquals(graph.shortestPath("Doha", "New York"), doh2nyc);

  }


  /**
   * This simple test checks whether the path costs returned by the backend method are correct. This
   * also returns an error message if the path costs displayed are incorrect or unexpected.
   */
  @Test
  public void TestPathCost() {


    assertTrue(graph.getPathCost("Madison", "Singapore") == 23,
        "Unfortunately the path costs does not seem to be right! Check your backend and try again");

    assertTrue(graph.getPathCost("Doha", "New York") == 20,
        "Unfortunately the path costs does not seem to be right! Check your backend and try again");

  }


  /**
   * Checks whether the vertex count returned by the backend is correct for the above data entered.
   */
  @Test
  public void TestVertexCount() {

    assertTrue(graph.getVertexCount() == 8,
        "# of vertices returned is incorrect. Check Backend and try again.");

  }

  

  /**
   * Checks whether the edge count returned by the backend is correct for the above data entered.
   */
  @Test
  public void TestEdgeCount() {

    assertTrue(graph.getEdgeCount() == 22,
        "# of edges returned is incorrect. Check Backend and try again.");

  }
}
