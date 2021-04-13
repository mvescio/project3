// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader:

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import org.junit.jupiter.api.Test;

public class DataReaderTest {
  /**
   * This method tests that the list of vertexes is correctly read from the file 
   * and put into the list of strings returned. The vertexes do not have a cost
   * and there are no corresponding destinations for these vertexes yet.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testVertex() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    test.readDataSet();
    List<String> list = test.vertices;
    if (!list.get(0).toString().equals("Dejope") && !list.get(1).toString().equals("Waters")) {
      fail(list.get(0).toString() + " " + list.get(1).toString());
    }
    
    if (!list.get(8).toString().equals("Witte")) {
      fail(list.get(8).toString());
    }
  }
  
  /**
   * This method tests that the destinations cost is correctly added to the 'cost'
   * characteristic of each destination from the csv file. The method throws an 
   * exception if the csv file is not found.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testEdges() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    test.readDataSet();
    Object[][] list = test.edges;
    if (!list[1][1].equals("Business School") && !list[2][1].equals("Union South")) {
      fail(list[1][1].toString() + " " + list[2][1].toString());
    }
  }
  
  /**
   * This method tests that the correct cost between the destinations is correctly
   * transferred from the csv file to the list of destinations.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testCost() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    test.readDataSet();
    Object[][] list = test.edges;
    if (!list[1][2].toString().equals("3") && !list[2][2].toString().equals("5")) {
      fail(list[1][2].toString() + " " + list[2][2].toString());
    }
  }
}