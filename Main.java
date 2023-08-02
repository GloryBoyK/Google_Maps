import java.io.*;
import java.util.*;

class Main {

  public static WeightedGraph<String, Double> graph;
  public static HashMap<String, Location> locations;
  public static Scanner scanner;

  public static void main(String[] args) {
    graph = new WeightedGraph<String, Double>();
    locations = new HashMap<String, Location>();
    System.out.println("Welcome to Street Searcher!");
    while (locations.size()<=0)
      loadData();
    System.out.println("LOCATIONS: ");
    for(String l : locations.keySet()){
      System.out.println(l + ": ");
    }
    System.out.println();
    //System.out.println(graph);
    PathFinder pf1 = new PathFinder(graph, locations);
    while(true){
      // Scanner start = new Scanner(System.in);
      System.out.println("Enter a starting point: ");
      String startPoint = scanner.nextLine();
      // start.close();
      // Scanner end = new Scanner(System.in);
      System.out.println("Enter an ending point: ");
      String endPoint = scanner.nextLine();
      // end.close();
      try{
        pf1.findShortestPath(locations.get(startPoint), locations.get(endPoint));
      }
      catch(NullPointerException e){
        System.out.println("One of your points does not exist. ");
      }
    }
  }
  
  public static void loadData(){
    try{
      graph = new WeightedGraph<String, Double>();
      locations = new HashMap<String, Location>();
      scanner = new Scanner(System.in);
      System.out.println("Please enter the file you would like loaded: ");
      String fileName = scanner.nextLine();
      File file = new File(fileName);
      Scanner fileScanner = new Scanner(file);
      System.out.println("Loading...");
      while(fileScanner.hasNext()){
        parseTokens(fileScanner.nextLine().split(" "));
      }
      // s.close();
      fileScanner.close();
      System.out.println("Loading Completed.");
      System.out.println("There are " +  graph.getEdgesCount() + " roads in this dataset.");
      System.out.println("There are " + locations.size() + " locations in this dataset");
      throw(new FileNotFoundException());
    }
    catch(FileNotFoundException e){
      System.out.println("ERROR: " + e.getMessage() + "The file you selected does not exist.");
    }
  }
  
  public static void parseTokens(String[] token){
    //take in front latitude and front longitude for the source
    //2nd item is the 2 latitude and 2 longitude for the destination
    //3rd is thew distance
    //last item is the source name and the destination (e.g. source--destinaion)
    //parse data with split function, then use the data to create its location object with which we add to the locations HashMap
    //(use the putIfAbsent())
    //[0] front latitude, fron longitude
    //[1] end latitude, end longitude
    //[2] distance
    //[3] front name -- end name
    String[] names = token[3].split("--");
    String fromName = names[0];
    String[] fromCoords = token[0].split(",");
    double distance = Double.parseDouble(token[2]);
    double fromLat = Double.parseDouble(fromCoords[0]);
    double fromLong = Double.parseDouble(fromCoords[1]);
    Location l1 = new Location(fromName, fromLat, fromLong);
    locations.putIfAbsent(fromName, l1);
    String endName = names[1];
    String[] endCoords = token[1].split(",");
    double endLat = Double.parseDouble(endCoords[0]);
    double endLong = Double.parseDouble(endCoords[1]);
    Location l2 = new Location(endName, endLat, endLong);
    locations.putIfAbsent(endName, l2);
    graph.addEdge(fromName, endName, distance);
  }
}
