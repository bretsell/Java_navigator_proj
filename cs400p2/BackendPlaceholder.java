import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class BackendPlaceholder implements BackendInterface {

  
  @Override
  public void readData(File file) throws FileNotFoundException {
    // TODO Auto-generated method stub
    if (file.getName().equals("invalid.txt")) {
      throw new FileNotFoundException("not found");
    }
  }

  @Override
  public ResultsUWNavigatorImplementation shortestPathHandler(String start, String destination) {
 // Placeholder implementation: It can be modified based on the actual shortest path algorithm.
    System.out.println("Placeholder: Finding the shortest path from " + start + " to " + destination);

    // Hardcoded values for testing
    List<String> path = Arrays.asList("BuildingA", "BuildingB", "BuildingC");
    List<Double> times = Arrays.asList(5.0, 3.0);
    double totalTime = 8.0;

    // Create an anonymous subclass of ResultsUWNavigator
    return new ResultsUWNavigatorImplementation(path, times, totalTime) {
        @Override
        public List<String> getPath() {
            return path;
        }

        @Override
        public List<Double> getTimes() {
            return times;
        }

        @Override
        public double getTotalTime() {
            return totalTime;
        }
    };
  }

  @Override
  public String statisticsHandler() {
    
    return "Stats";
  }

}
