import java.util.*;
public class PathFinder{
  public static WeightedGraph<String, Double> graph;
  public static HashMap<String, Location> locations;
  public static PriorityQueue<Location> unvisited;
  
  public PathFinder(WeightedGraph<String, Double> graph, HashMap<String, Location> locations){
    this.graph = graph;
    this.locations = locations;
  }
  
  public void printShortestPath(){
    
  }
  
  public void findShortestPath(Location start, Location end){
    for(Location l1 : locations.values()){
      l1.setPrevious(null);
      l1.setDistance(Double.MAX_VALUE);
    }
    start.setDistance(0);
    unvisited = new PriorityQueue<Location>();
    for(Location l1 : locations.values()){
      unvisited.add(l1);
    }
    Location nearestNeighbor;
    while(unvisited.contains(end)){
      nearestNeighbor = unvisited.poll();
      updateNeighborDistances(nearestNeighbor);
    }
    System.out.println("Total Distance: " + getTotalDistance(end) + " miles.");
    printPath(end);
  }
  
    public double getTotalDistance(Location location){
      double distance = 0.0;
      Location prev = location.getPrevious();
      if(prev == null){
        return 0;
      }
      else{
        String name = location.getName();
        String previousName = location.getPrevious().getName();
        distance = graph.getEdges(previousName).get(name);
        return distance + getTotalDistance(location.getPrevious());
      }
    }
  
    public void updateNeighborDistances(Location nearest){
      HashMap<String, Double> roads = graph.getEdges(nearest.getName());
      for(String locationName : roads.keySet()){
        Location keyMatch = locations.get(locationName);
        // keyMatch = vertices.get(neighborIndex);
        if(unvisited.contains(keyMatch)){
          double nCurrent=keyMatch.getDistance();
          double alternate = nearest.getDistance() + roads.get(locationName);
          if(nCurrent>alternate){
            keyMatch.setDistance(alternate);
            keyMatch.setPrevious(nearest);
            unvisited.remove(keyMatch);
            unvisited.add(keyMatch);
          }
        }
      }
  }
  
  public void printPath(Location location){
    String path = "";
    Location previous = location.getPrevious();
    if(previous == null){
      return;
    }
    printPath(previous);
    double distance = graph.getEdges(previous.getName()).get(location.getName());
    path += (previous.getName() + " --> " + location.getName() + " (" + distance + ") miles" + "\n");
    System.out.print(path);
  }
}
