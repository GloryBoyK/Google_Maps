import java.util.*; 
  
public class WeightedGraph<T, W> implements Network<T,W>{ 
  
  private Map<T, HashMap<T, W>> map = new HashMap<>(); 
  private int numEdges = 0;

  //WRITE YOUR CODE HERE:
  //NOTE: this class won't compile until all the methods in the interface are implmented
  
  public int getVertexCount(){
    return map.size();
  }
  
  public int getEdgesCount(){
    return numEdges;
  }
  
  public boolean hasVertex(T vertex){
    return map.containsKey(vertex);
  }
  
  public boolean hasEdge(T source, T destination){
    if(hasVertex(source)){
      return map.get(source).containsKey(destination);
    }
    return false;
  }
  
  public void addVertex(T vertex){
    if(!this.hasVertex(vertex)){
      map.put(vertex, new HashMap<T, W>());
    }
    else{
      return;
    }
  }
  
  public void addEdge(T source, T destination){
    addEdge(source, destination, null);
  }
  
  public void addEdge(T source, T destination, W weight){
    addVertex(source);
    addVertex(destination);
    if(!hasEdge(source, destination)){
      map.get(source).put(destination, weight);
      numEdges++;
    }
  }
  
  @Override
  public HashMap<T,W> getEdges(T vertex){
    if(hasVertex(vertex)){
      return map.get(vertex);
    }
    return null;
  }
  
  //TOSTRING ------------------------------------------
  @Override
  public String toString() { 
    StringBuilder builder = new StringBuilder(); 
    for (T vertex : map.keySet()) { 
      builder.append(vertex.toString() + ": "); 

      HashMap<T, W> weights = map.get(vertex);
      for (T edge : weights.keySet()) { 
        builder.append(edge.toString());
        builder.append("(" + weights.get(edge) + ") "); 
      } 
      builder.append("\n"); 
    } 
    return (builder.toString()); 
  }
  
}
