import java.io.FileNotFoundException;

public interface FrontendInterface {
 /**
  * Constructor for the Frontend.
  *
  * @param backend A reference to the backend that the frontend will interact with.
  * @param scanner A Scanner instance for reading user input.
  */
 // public Frontend(Backend backend, Scanner scanner);
  /**
  * This method starts the main menu (main command loop) for the users
  */
 public void startMainMenu();
 /**
  * Handles the command to load a data file. Prompts the user for the file path and passes it to
  * the backend for processing.
  *
  * @param fileName - name of file to be used
  * @throws IllegalArgumentException if file is not in correct format
  * @throws NullPointerException     if the file selected is null
  */
 public void loadDataFile(String fileName) throws FileNotFoundException;
 /**
  * Handles the command to show statistics about the dataset.
  * Retrieves the statistics from the backend and displays them to the user.
  *
  * @param statics - the string containing number of nodes, edges, and time
  * @throws NullPointerException     if the String is null
  */
 public void showStatistics();
 /**
  * Handles the command to find the shortest path between two buildings.
  * Prompts the user for the start and destination buildings, then displays the path details.
  *
  * @param shortestPath - a list of the data for the shortest path
  * @throws NullPointerException     if the list is null
  */
 public void findShortestPath(String start, String destination);
 /**
  * Handles the command to exit the application.
  */
 public void exitApp();
}

