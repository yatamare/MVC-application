package assignment3;

import java.util.ArrayList;

/**
* HRM184
 */
public class GraphModel{
   
    private ArrayList<Vertex> circleList;
    private ArrayList<Edge> edgeList;
    private ArrayList<GraphView> subscribers;
    
    public GraphModel(){
        circleList = new ArrayList<>();
        edgeList = new ArrayList<>();
        subscribers = new ArrayList<>();
    }
    
    public void AddCircle(Vertex newCircle){
        circleList.add(newCircle);
        NotifySubscribers();
    }
    
    public void RemoveCircle(Vertex oldCircle){
        circleList.remove(oldCircle);
        NotifySubscribers();
    }
    
    public void AddEdge(Edge newEdge){
        edgeList.add(newEdge);
        NotifySubscribers();
    }
    
    public void RemoveEdge(Edge oldEdge){
        edgeList.remove(oldEdge);
        NotifySubscribers();
    }
    
    public void NotifyEdges(){
        for (int i = 0; i < edgeList.size(); i += 1){
            edgeList.get(i).ModelChanged();
        }
    }
    
    public void AddSubscriber(GraphView newSub){
        subscribers.add(newSub);
    }
    
    public void NotifySubscribers(){
        for (int i = 0; i < subscribers.size(); i += 1){
            subscribers.get(i).modelChanged();
        }
    }
    
    public void RemoveSubscriber(GraphView oldSub){
        subscribers.remove(oldSub);
    }
    
    public Vertex Contains(double x, double y){
        for (int i = 0; i < circleList.size(); i += 1){
            if (circleList.get(i).Contains(x,y)){
                return circleList.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<Vertex> GetCircleList(){
        return circleList;
    }
    
    public ArrayList<Edge> GetEdgeList(){
        return edgeList;
    }
    
}
