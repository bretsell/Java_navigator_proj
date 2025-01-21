import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {
  private final BackendInterface backend;
  private final Scanner scanner;


  public Frontend(BackendImplementation backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
  }

  public Frontend(BackendPlaceholder backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
  }

  @Override
  public void startMainMenu() {
    boolean exit = false;
    while (!exit) {
      System.out.println("Main Menu:");
      System.out.println("1. Load Data File");
      System.out.println("2. Show Statistics");
      System.out.println("3. Find Shortest Path");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");

      try {
        int choice = scanner.nextInt();
        scanner.nextLine(); // get the nextLine character

        switch (choice) {
          case 1:
            System.out.print("Enter the file name: ");
            String fileName = scanner.nextLine();
            try {
              loadDataFile(fileName);
            } catch (FileNotFoundException e) {
              System.out.println("File not found: " + fileName);
            } catch (IllegalArgumentException e) {
              System.out.println("Invalid file format: " + e.getMessage());
            }
            break;
          case 2:
            showStatistics();
            break;
          case 3:
            System.out.print("Enter the start building: ");
            String start = scanner.nextLine();
            System.out.print("Enter the destination building: ");
            String destination = scanner.nextLine();
            findShortestPath(start, destination);
            break;
          case 4:
            exitApp();
            exit = true;
            break;
          default:
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.nextLine(); // clear the buffer
      }
    }
  }

  @Override
  public void loadDataFile(String fileName) throws FileNotFoundException {
    if (fileName == null) {
      throw new NullPointerException();
    }

    try {
      File file = new File(fileName);

      backend.readData(file);
      System.out.println("Data file loaded successfully.");
    } catch (FileNotFoundException e) {
      throw e; // The exception if file is not found
    }
  }

  @Override
  public void showStatistics() {
    String statistics = backend.statisticsHandler();
    if (statistics == null) {
      throw new IllegalStateException("No statistics available for this file.");
    }
    System.out.println(statistics);
  }

  @Override
  public void findShortestPath(String start, String destination) {

    if (start.equals(destination)) {
      System.out.println("Error finding the shortest path.");
    }

    if (!start.equals(destination)) {
      try {
        ResultsUWNavigatorImplementation shortestPath =
            backend.shortestPathHandler(start, destination);
        if (shortestPath.getPath() != null) {
          List<String> path = shortestPath.getPath();
          List<Double> times = shortestPath.getTimes();

          System.out.println("Shortest Path Details:");

          for (int i = 0; i < path.size() - 1; i++) {
            System.out.println(path.size());
            String currentBuilding = path.get(i);
            String nextBuilding = path.get(i + 1);
            double time = times.get(i);

            System.out.println(
                "From " + currentBuilding + " to " + nextBuilding + ", Walking Time: " + time);
          }

          System.out.println("Total Walking Time: " + shortestPath.getTotalTime());
        } else {
          System.out.println("Error finding the shortest path.");
        }
      } catch (NullPointerException e) {
        System.out.println("Error building not found.");
      }
    }
  }

  @Override
  public void exitApp() {
    System.out.println("Exiting the application. Goodbye!");
  }

  /**
   * Main method for this class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    // create a scanner and backend object to pass to created frontend object.
    Scanner in = new Scanner(System.in);
    BackendImplementation backend = new BackendImplementation();
    Frontend frontend = new Frontend(backend, in);
    frontend.startMainMenu();
  }
}
