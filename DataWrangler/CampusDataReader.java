// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class CampusDataReader implements CampusMapDataReaderInterface{

  @Override
  public List<Destination> readDataSet() 
      throws FileNotFoundException, IOException, DataFormatException{
  /**
   * Reads in a set of Destinations from a .csv file and creates a Destination object based
   * off of each line. Adds all of the Destinations to an ArrayList of Destination objects
   * 
   * @param FileReader inputFileReader FileReader object to read the .csv file
   * 
   * @throws FileNotFoundException if the file cannot be located
   * 
   * @throws IOException if it is not a file and if it cannot be read
   * 
   * @throws DataFormatException when the file does not have the correct format.
   */
      List<Destination> destinationList = new ArrayList<Destination>();
      File f = new File("CampusMap.csv"); // May need to change once csv file is made

      if (!f.exists()) {
          throw new FileNotFoundException();
      }

      if (!f.isFile() || !f.canRead()) {
          throw new IOException();
      }

      // checks for correct format
      if (!f.getName().contains(".csv")) {
          throw new DataFormatException();
      }

      FileReader inputFileReader = new FileReader(f);
      BufferedReader csvReader = new BufferedReader(inputFileReader);

      String row; // row of text from .csv file
      csvReader.readLine(); // reads the first placeholder line

      // loop where it runs until there isn't a next line
      while ((row = csvReader.readLine()) != null) {

          // creates an Object array to hold the Destinations' data before casting them to
          // their correct type
          Object[] data = new Object[3];
          int startIndex = 0;
          int endIndex = row.indexOf(",");

          for (int i = 0; i < data.length; i++) {
              if (endIndex < 0) { // will prevent errors when adding the last item in a line
                  data[i] = row;
              } else { // in most cases, add item to data and update the substring
                  data[i] = row.substring(startIndex, endIndex);
                  row = row.substring(endIndex + 1);
                  endIndex = row.indexOf(",");
              }
          }

          // converts the info from each Destination and adds to the Destination object

          // name stored at index 0
          String name = data[0].toString();
          
          // cost stored at index 1
          String costString = ((String) data[1]);
          int cost1 = Integer.parseInt(costString);
          Integer cost = ((Integer) cost1);

          // time stored at index 2
          String timeString = ((String) data[2]);
          int time1 = Integer.parseInt(timeString);
          Integer time = ((Integer) time1);

          Destination d = new Destination(name, cost, time);

          // then add the new object to the destinationList, which gets returned after closing
          // the file
          destinationList.add(d);
      }

      csvReader.close();
      inputFileReader.close();
      return destinationList;
  }

}
