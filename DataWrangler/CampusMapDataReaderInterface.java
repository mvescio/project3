// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

public interface CampusMapDataReaderInterface {

  public void readDataSet()
      throws FileNotFoundException, IOException, DataFormatException;
}

