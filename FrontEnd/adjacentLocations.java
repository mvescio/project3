import java.util.*;

public class adjacentLocations<T> extends CS400Graph<T> {
  


  public static void main(String[] args) {
    CS400Graph<Integer> adj = new CS400Graph<>();
    adj.insertVertex(1);
    adj.insertVertex(2);
    adj.insertVertex(3);
    adj.insertVertex(4);
    adj.insertVertex(5);
    adj.insertEdge(1, 2, 3);
    adj.insertEdge(2, 1, 3);
    adj.insertEdge(1, 5, 6);
    adj.insertEdge(5, 1, 6);
    adj.insertEdge(2, 3, 5);
    adj.insertEdge(3, 2, 5);
    adj.insertEdge(3, 4, 7);
    adj.insertEdge(4, 3, 7);
    adj.insertEdge(4, 5, 9);
    adj.insertEdge(5, 4, 9);
    
    System.out.println("Please choose your start location from the following:");
    System.out.println("{1}");
    System.out.println("{2}");
    System.out.println("{3}");
    System.out.println("{4}");
    System.out.println("{5}");
    Scanner sc = new Scanner(System.in);
    int input = sc.nextInt();
    sc.close();
    
    for (CS400Graph.Vertex v : adj.vertices.values()) {
      //System.out.println(v.data);
      if ((int)v.data== input) {
        System.out.println("The adjacent edges to your origin are:");
        for (CS400Graph.Edge e : (LinkedList<CS400Graph.Edge>)v.edgesLeaving) {
        
        System.out.println(((CS400Graph.Vertex)e.target).data);
      }
      }
    }

  }

}
