// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: I used our team's data wrangling code from the last project
// as a reference
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DataFormatException;

public interface CampusMapDataReaderInterface {

  public List<Destination> readDataSet()
      throws FileNotFoundException, IOException, DataFormatException;
}

