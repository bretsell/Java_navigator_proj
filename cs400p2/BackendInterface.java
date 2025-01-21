import java.io.File;
import java.io.FileNotFoundException;

public interface BackendInterface {
  /*
   * This is a constructor for the backend interface that passes a GraphADT instance.
   */

  // public void IndividualBackendInterface(GraphADT object);


  /*
   * This method reads the data from a user input file (parameter).
   *
   * @param file
   *
   * @throws FileNotFoundException if input file does not exist
   */
  public void readData(File file) throws FileNotFoundException;

  /*
   * This method gets the shortest path between the input starting point and destination.
   *
   * @param start - starting point
   *
   * @param destination - end point
   * 
   * @return 
   */
  public ResultsUWNavigatorImplementation shortestPathHandler(String start, String destination);

  /*
   * This method returns a string with all the statistics of the trip input by the user. It returns
   * the number of nodes (buildings), the number of edges, and the total walking time (sum of
   * weights) for all edges in the graph
   *
   * @return String of all statistics
   */
  public String statisticsHandler();
}
