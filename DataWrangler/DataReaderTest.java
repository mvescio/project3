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
   * This method tests that the destinations name is correctly added to the 'name'
   * characteristic of each destination from the csv file. The method throws an 
   * exception if the csv file is not found.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testNameMethod() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (!list.get(0).getName().equals("Union South")) {
      fail(list.get(0).getName());
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
  void testCostMethod() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (list.get(0).getCost() != 4) {
      fail(list.get(0).getCost().toString());
    }
  }
  
  /**
   * This method tests that the destinations time is correctly added to the 'time'
   * characteristic of each destination from the csv file. The method throws an 
   * exception if the csv file is not found.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testTimeMethod() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (list.get(0).getTime() != 15) {
      fail(list.get(0).getTime().toString());
    }
  }
  
  /**
   * This method tests the compare to method. The result should be a postive 1
   * because the destination cost of 'this' destination is greater than the cost
   * of the other destination. The cost of the first destination is 4, while the 
   * cost of the second destination is 2. The method throws an exception if the 
   * csv file is not found.
   * @return true if the test passes, and false otherwise
   * @throws DataFormatException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Test
  void testCompareToMethod() throws FileNotFoundException, IOException, DataFormatException {
    CampusDataReader test = new CampusDataReader();
    List<Destination> list = test.readDataSet();
    if (list.get(0).compareTo(list.get(1)) != 1) {
      fail(list.get(0).getTime().toString());
    }
  }
}
