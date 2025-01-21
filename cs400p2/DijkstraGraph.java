// --== CS400 File Header Information ==--
// Name: Bret Sell
// Email: brsell@wisc.edu
// Group and Team: E31
// Group TA: Nicholas Russell
// Lecturer: Gary Dahl

import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * This class extends the BaseGraph data structure with additional methods for computing the total
 * cost and list of node data along the shortest path connecting a provided starting to ending
 * nodes. This class makes use of Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode contains data about one
   * specific path between the start node and another node in the graph. The final node in this path
   * is stored in its node field. The total cost of this path is stored in its cost field. And the
   * predecessor SearchNode within this path is referened by the predecessor field (this field is
   * null within the SearchNode containing the starting node in its node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost SearchNode has the
   * highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;

    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }

    public int compareTo(SearchNode other) {
      if (cost > other.cost)
        return +1;
      if (cost < other.cost)
        return -1;
      return 0;
    }
  }

  /**
   * Constructor that sets the map that the graph uses.
   * 
   * @param map the map that the graph uses to map a data object to the node object it is stored in
   */
  public DijkstraGraph(MapADT<NodeType, Node> map) {
    super(map);
  }

  /**
   * This helper method creates a network of SearchNodes while computing the shortest path between
   * the provided start and end locations. The SearchNode that is returned by this method is
   * represents the end of the shortest path that is found: it's cost is the cost of that shortest
   * path, and the nodes linked together through predecessor references represent all of the nodes
   * along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found or when either start or
   *                                end data do not correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end) {
    // Map to keep track of visited nodes during the traversal
    PlaceholderMap<NodeType, Boolean> visitNodes = new PlaceholderMap<>();
    PriorityQueue<SearchNode> priorityQueue = new PriorityQueue<>();

    // Retrieve start and end nodes from the graph
    Node startNode = nodes.get(start);
    Node endNode = nodes.get(end);

    // Check if start and end nodes are present in the graph
    if (startNode == null || endNode == null) {
      throw new NoSuchElementException("Start or end node not found in the graph");
    }

    priorityQueue.add(new SearchNode(startNode, 0, null));

    // Perform Dijkstra's algorithm until the priority queue is empty
    while (!priorityQueue.isEmpty()) {
      SearchNode current = priorityQueue.poll();

      // Skip processing if the node has already been visited
      if (visitNodes.containsKey(current.node.data)) {
        continue;
      }
      visitNodes.put(current.node.data, true);

      if (current.node.data.equals(end)) {
        return current; // Found the shortest path to the end node
      }

      // Explore neighbors of the current node and update costs in the priority queue
      for (Edge edge : current.node.edgesLeaving) {
        Node next = edge.successor;
        
        if (!visitNodes.containsKey(next.data)) {
          double newCost = current.cost + edge.data.doubleValue();
          priorityQueue.add(new SearchNode(next, newCost, current));
        }
      }
    }

    //if no path throw exception
    throw new NoSuchElementException("No path found from start to end");
  }

  /**
   * Returns the list of data values from nodes along the shortest path from the node with the
   * provided start value through the node with the provided end value. This list of data values
   * starts with the start value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {

    // Compute the shortest path using Dijkstra's algorithm
    SearchNode result = computeShortestPath(start, end);
    LinkedList<NodeType> path = new LinkedList<>();

    // Reconstruct the path from the result
    while (result != null) {
      path.addFirst(result.node.data);
      result = result.predecessor;
    }

    // Check if the reconstructed path is valid
    if (path.getFirst().equals(start) && path.getLast().equals(end)) {
      return path; // Return the path if it is valid
    } else {
      throw new NoSuchElementException("No valid path from start to end");
    }
  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest path freom the node
   * containing the start data to the node containing the end data. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) {
    // Compute the shortest path using Dijkstra's algorithm
    SearchNode result = computeShortestPath(start, end);

    // Check if the result corresponds to the destination node
    if (result.node.data.equals(end)) {
      return result.cost; // Return the cost of the shortest path
    } else {
      throw new NoSuchElementException("No valid path from start to end");
    }
  }

  // TODO: implement 3+ tests in step 4.1
  public static DijkstraGraph<String, Integer> createLectureGraph() {
    DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());

    graph.insertNode("A");
    graph.insertNode("B");
    graph.insertNode("D");
    graph.insertNode("E");
    graph.insertNode("F");
    graph.insertNode("G");
    graph.insertNode("H");
    graph.insertNode("I");
    graph.insertNode("L");
    graph.insertNode("M");

    graph.insertEdge("A", "B", 1);
    graph.insertEdge("A", "H", 8);
    graph.insertEdge("A", "M", 5);
    graph.insertEdge("B", "M", 3);
    graph.insertEdge("M", "F", 4);
    graph.insertEdge("M", "E", 3);
    graph.insertEdge("F", "G", 9);
    graph.insertEdge("G", "L", 7);
    graph.insertEdge("D", "G", 2);
    graph.insertEdge("D", "A", 7);
    graph.insertEdge("I", "D", 1);
    graph.insertEdge("I", "L", 5);
    graph.insertEdge("I", "H", 2);
    graph.insertEdge("H", "B", 6);
    graph.insertEdge("H", "I", 2);

    return graph;
  }

  /*
   * This test checks that the shortest path cost is calculated correctly.
   */
  @Test
  public void test1() {
    DijkstraGraph<String, Integer> graph = createLectureGraph();

    double cost = graph.shortestPathCost("A", "G");
    
    assertEquals(cost, 13, "shortestPathCost not correct");
  }

  /*
   * This test checks that the shortest path data is correctly added for the shortest path.
   */
  @Test
  public void test2() {
    DijkstraGraph<String, Integer> graph = createLectureGraph();

    List<String> path = graph.shortestPathData("A", "F");
    double cost = graph.shortestPathCost("A", "F");

    assertEquals(path.toString(), "[A, B, M, F]", "shortestPathData not correct");
    assertEquals(cost, 8, "shortestPathCost not correct");
  }

  /*
   * This test checks for an error when there is not path between choosen nodes.
   */
  @Test
  public void test3() {
    DijkstraGraph<String, Integer> graph = createLectureGraph();

    assertThrows(NoSuchElementException.class, () -> graph.shortestPathCost("E", "B"));
  }

}
