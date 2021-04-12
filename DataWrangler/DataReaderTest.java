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
   * and put into the list of destinations returned. The vertexes have a cost of 
   * 0 and there are no corresponding destinations for these vertexes yet.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testVertexAddition() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (!list.get(0).getName().equals("Dejope") && !list.get(1).getName().equals("Waters")) {
      fail(list.get(0).getName() + " " + list.get(1).getName());
    }
    
    if (!list.get(8).getName().equals("Witte")) {
      fail(list.get(8).getName());
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
  void testDestinations() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (!list.get(9).getDest().equals("Hummanities") && !list.get(10).getDest().equals("Business")) {
      fail(list.get(9).getCost() + " " + list.get(10).getCost());
    }

  }
  
  /**
   * This method tests that the correct cost between the destinations is correcetly
   * transferred from the csv file to the list of destinations.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testCost() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (list.get(9).getCost() !=  3 && list.get(10).getCost() != 3 && list.get(11).getCost() == 9) {
      fail(list.get(9).getCost() + " " + list.get(10).getCost());
    }
  }
  
  /**
   * This method tests that the items that are farther down the list also got added
   * to the list of destinations and have the correct names with destinations.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testAllElements() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (!list.get(20).getName().equals("Business") || !list.get(20).getDest().equals("Hummanities")) {
      fail(list.get(20).getDest());
    }
    
    if (!list.get(21).getName().equals("Business") || !list.get(21).getDest().equals("Van Vleck Hall")) {
      fail(list.get(21).getDest());
    }
  } 
}
