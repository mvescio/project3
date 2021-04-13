// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: I used our team's data wrangling code from the last project
// as a reference

/**
 * This class represents a Destination Object which contains a set of
 * characteristics (name, cost, and time) for each Destination as well
 * as a method that allows Destination objects to be compared by their cost.
 * 
 * @author Sydney Benck
 */
public class Destination implements DestinationInterface{
  private String name;
  private String dest;
  private Integer cost;
  
  /**
   * Constructor which creates the Destination object and stores the Destination's 
   * name, cost, and time
   */
  public Destination(String name, String dest, Integer cost) {
      this.name = name;
      this.dest = dest;
      this.cost = cost;
  }  
  
  /**
   * Returns this Destinations name.
   */
  @Override
  public String getName() {
    return this.name;
  }
  
  /**
   * Returns this Destinations cost.
   */
  @Override
  public Integer getCost() {
    return this.cost;
  }
  
  /**
   * Returns this Destinations cost.
   */
  @Override
  public String getDest() {
    return this.dest;
  }

  /**
   * Compares two Destination Objects by their cost
   * 
   * @param otherDestination the Destination object to compare to
   * @return 1 if this Destination has a larger cost than the otherDestination
   * @return -1 if this Destination has a smaller cost than the otherDestination
   * @return 0 if the Destination's costs are the same
   */
  @Override
  public int compareTo(DestinationInterface otherDestination) {
      if (this.cost > otherDestination.getCost()) {
          return 1;
      } else if (this.cost < otherDestination.getCost()) {
          return -1;
      } else {
          return 0;
      }
  }
}