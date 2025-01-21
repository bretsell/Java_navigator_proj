import java.util.List;

@SuppressWarnings("rawtypes")
public class ResultsUWNavigatorImplementation implements ResultsUWNavigator {
    private List<String> buildingPath;
    private List<Double> times;
    private double totalTime;
    
    public ResultsUWNavigatorImplementation(List<String> path, List<Double> time, double total) {
        this.buildingPath = path;
        this.times = time;
        this.totalTime = total;
    }
    @Override
    public List<String> getPath() {
        return this.buildingPath;
    }

    @Override
    public List<Double> getTimes() {
        return this.times;
    }

    @Override
    public double getTotalTime() {
        return this.totalTime;
    }

}
