import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrontendDeveloperTests {

  @Test
  public void testLoadDataFileSuccess() {
    // Simulate user input for loading a valid data file
    String input = "1\nvalid_data.txt\n4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the success message
    String output = tester.checkOutput();
    assertTrue(output.contains("Data file loaded successfully."));
  }

  @Test
  public void testLoadDataFileNotFound() {
    // Simulate user input for loading a non-existent data file
    String input = "1\ninvalid.txt\n4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the file not found message
    String output = tester.checkOutput();
    assertTrue(output.contains("File not found: invalid.txt"));
  }

  @Test
  public void testShowStatistics() {
    // Simulate user input for showing statistics
    String input = "2\n4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the statistics
    String output = tester.checkOutput();
    assertTrue(output.contains("Stats"));
  }

  @Test
  public void testFindShortestPath() {
    // Simulate user input for finding the shortest path
    String input = "3\nchem\nunion\n4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the shortest path details
    String output = tester.checkOutput();
    assertTrue(output.contains("Shortest Path Details:"));
  }

  @Test
  public void testExitApp() {
    // Simulate user input for exiting the application
    String input = "4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the exit message
    String output = tester.checkOutput();
    assertTrue(output.contains("Exiting the application. Goodbye!"));
  }

  @Test
  // This integration test checks if the statisticsHandler method in the BackendImplementation class
  // produces the expected output when called after reading data from a sample file "campus.dot".
  public void testIntegration1() {
    String input = "1\ncampus.dot\n2\n4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendImplementation(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the exit message
    String output = tester.checkOutput();
    assertTrue(output.contains(
        "Statistics of trip - Buildings on the way: 160 | Connections between buildings: 800 | Total walk time between all buildings: 110675.0"));
  }

  @Test
  // This integration test checks if the shortestPathHandler method in the BackendImplementation
  // class produces the expected output for a specific path between "Memorial Union" and "Wendt
  // Commons" when using the frontend methods.
  public void testIntegration2() {
    String input = "1\ncampus.dot\n3\nMemorial Union\nWendt Commons\n4\n";
    TextUITester tester = new TextUITester(input);

    // Run the frontend
    Frontend frontend = new Frontend(new BackendImplementation(), new Scanner(System.in));
    frontend.startMainMenu();

    // Check if the output contains the exit message
    String output = tester.checkOutput();
    assertTrue(output.contains("Memorial Union") && output.contains("Wendt Commons")
        && output.contains("1510.5"));
  }

  @Test
  // This unit test checks if the statisticsHandler method in the BackendImplementation class
  // produces the expected output when called without reading any data.
  public void BackendTest1() {
    BackendImplementation test = new BackendImplementation();

    String actualPath = test.statisticsHandler();
    assertEquals(
        "Statistics of trip - Buildings on the way: 0 | Connections between buildings: 0 | Total walk time between all buildings: 0.0",
        actualPath);
  }

  @Test
  // This unit test checks if the shortestPathHandler method in the BackendImplementation class
  // produces null output when trying to find the shortest path between two non-existing buildings.
  public void BackendTest2() {
    BackendImplementation test = new BackendImplementation();
    File file = new File("campus.dot");
    try {
      test.readData(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(test.shortestPathHandler("\"Radio Hall\"", "\"Not a Place\""), null);
  }

}
