// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader:

public interface DestinationInterface extends Comparable<DestinationInterface> {
  public String getName();

  public Integer getCost();
  
  public String getDest();

  // from super interface Comparable
  public int compareTo(DestinationInterface otherDestination);


}
