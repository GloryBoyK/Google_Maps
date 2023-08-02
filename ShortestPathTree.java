import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
public class ShortestPathTree{ 

  WeightedGraph<String, Double> graph;
  HashMap<String, Location> locations;
  PriorityQueue<Location<Integer>> unvisited;
  
  public void dijkstra(int graph[][], int source){
    this.graph=graph;
    vertices=new ArrayList<>();
    unvisited=new PriorityQueue<>();
    for(int i=0; i<graph.length; i++){
      Vertex<Integer> vert=new Vertex<>(i);
      vert.setDistance(Integer.MAX_VALUE);
      vertices.add(vert);
    }
    vertices.get(source).setDistance(0);
    for(Vertex<Integer> v:vertices){
      unvisited.add(v);
    }
    Vertex<Integer> smallest;
    while(unvisited.isEmpty()==false){
      smallest=unvisited.poll();
      updateNeighborDistances(smallest);
    }
    printSolution();
  }
  
  public void updateNeighborDistances(Location shortest){
    // int index=shortest.getLabels();
    Hashmap<String, Double> roads = new HashMap<>();
    roads = graph.getEdges(shortest.getName());
    
    for(int neighborIndex=0; neighborIndex<graph[index].length; neighborIndex++){
      if(graph[index][neighborIndex] != 0){
        Vertex neighbor=vertices.get(neighborIndex);
        if(unvisited.contains(neighbor)){
          int nCurrent=neighbor.getDistance();
          int alternate = shortest.getDistance()+graph[index][neighborIndex];
          if(nCurrent>alternate){
            neighbor.setDistance(alternate);
            neighbor.setPrevious(shortest);
            unvisited.remove(neighbor);
            unvisited.add(neighbor);
          }
        }
      }
    }
  }
  public void printSolution(){
    for(Vertex<Integer> v:vertices){
      System.out.println(v);
    }
  }
    
  }
 
