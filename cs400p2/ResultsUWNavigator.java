import java.util.List;

public interface ResultsUWNavigator<T extends Comparable<T>> {
  /**
   * ResultsUWNavigator<T> ResultsUWNavigator = new ResultsUWNavigator(List<T> shortestPath); private List<String>
   * path; private List<Double> travelTimes; private double totalTime;
   */
  /*
   * This method will return a List of buildings that will be visited in between the initial and
   * final destination
   */
  public List<String> getPath();

  /*
   * This method will return a List of times that it takes from getting one building to the next
   * (using the getter method getPath() as its helper)
   */
  public List<Double> getTimes();

  /*
   * This method will return a time value that will add all the times from the getter method
   * getTimes()
   */
  public double getTotalTime();
}
