import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BackendImplementation implements BackendInterface {
    private double totalCost;
    DijkstraGraph<String, Double> buildingGraph = new DijkstraGraph<String, Double>(new PlaceholderMap<>());

    @Override
    public void readData(File file) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                try {
                    String data = reader.nextLine();
                    int firstIndex = data.indexOf("--");
                    int secondIndex = data.indexOf("[seconds=");
                    String building1 = data.substring(0, firstIndex).trim();
                    building1 = building1.substring(1, building1.length()-1);
                    String building2 = data.substring(firstIndex + 3, secondIndex).trim();
                    building2 = building2.substring(1, building2.length()-1);
                    String stringCost = data.substring(secondIndex).replace("[seconds=", "").replace("];", "").trim();
                    double cost = Double.parseDouble(stringCost);
                    buildingGraph.insertNode(building1);
                    buildingGraph.insertNode(building2);
                    buildingGraph.insertEdge(building1, building2, cost);
                    totalCost += cost;
                } catch (StringIndexOutOfBoundsException e) {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No file exists");
        }

    }

    @Override
    public ResultsUWNavigatorImplementation shortestPathHandler(String start, String destination) {
        if (buildingGraph.containsNode(start) && buildingGraph.containsNode(destination)) {
            List<String> trip = buildingGraph.shortestPathData(start, destination);
            List<Double> tripTimes = new ArrayList<Double>();

            for (int i = 0; i < trip.size()-1; i++) {
                tripTimes.add(buildingGraph.shortestPathCost(trip.get(i), trip.get(i + 1)));
            }
            double totalTime = buildingGraph.shortestPathCost(start, destination);
            ResultsUWNavigatorImplementation toReturn = new ResultsUWNavigatorImplementation(trip, tripTimes,
                    totalTime);
            return toReturn;
        }
        return null;
    }

    @Override
    public String statisticsHandler() {
        int buildingsVisited = buildingGraph.getNodeCount();
        int totalWalks = buildingGraph.getEdgeCount();
        double totalTime = Math.round(totalCost);
        return "Statistics of trip - Buildings on the way: " + buildingsVisited + " | Connections between buildings: "
                + totalWalks + " | Total walk time between all buildings: " + totalTime;
    }

}
