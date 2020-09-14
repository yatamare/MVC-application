package assignment3;

import java.util.ArrayList;

/**
 *HRM184
 */
public class InteractionModel {
    
    // subscribers
    private ArrayList<GraphView> subscribers;
    
    // selected
    private Vertex vSelected;
    private Edge eSelected;
    
    // size
    private int widgetHeight;
    private int widgetWidth;
    private int logicalHeight;
    private int logicalWidth;
    private int miniMapHeight;
    private int miniMapWidth;
    
    // view position
    private double locationX;
    private double locationY;
    
    public InteractionModel(int wHeight, int wWidth, int lHeight, int lWidth){
        subscribers = new ArrayList<>();
        widgetHeight = wHeight;
        widgetWidth = wWidth;
        logicalHeight = lHeight;
        logicalWidth = lWidth;
        locationX = 0;
        locationY = 0;
    }
    
    // Subscribers
    public void NotifySubscribers(){
        for (int i = 0; i < subscribers.size(); i += 1){
            subscribers.get(i).modelChanged();
        }
    }
    
    public void AddSubscriber(GraphView newSub){
        subscribers.add(newSub);
    }
    
    public void RemoveSubscriber(GraphView oldSub){
        subscribers.remove(oldSub);
    }
    
    // Get'ers && Set'ers
    public Vertex GetVertexSelected(){
        return vSelected;
    }
    
    public void SetVertexSelected(Vertex newVertex){
        vSelected = newVertex;
        NotifySubscribers();
    }
    
    public Edge GetEdgeSelected(){
        return eSelected;
    }
    
    public void SetEdgeSelected(Edge newEdge){
        eSelected = newEdge;
    }
    
    public int GetWidgetHeight(){
        return widgetHeight;
    }
    
    public void SetWidgetHeight(int newHeight){
        widgetHeight = newHeight;
    }
    
    public int GetWidgetWidth(){
        return widgetWidth;
    }
    
    public void SetWidgetWidth(int newWidth){
        widgetWidth = newWidth;
    }
    
    public int GetLogicalHeight(){
        return logicalHeight;
    }
    
    public void SetLogicalHeight(int newHeight){
        logicalHeight = newHeight;
    }
    
    public int GetLogicalWidth(){
        return logicalWidth;
    }
    
    public void SetLogicalWidth(int newWidth){
        logicalWidth = newWidth;
    }
    
    public int GetMiniMapWidth(){
        return miniMapWidth;
    }
    
    public void SetMiniMapWidth(int newMiniWidth){
        miniMapWidth = newMiniWidth;
    }
    
    public int GetMiniMapHeight(){
        return miniMapHeight;
    }
    
    public void SetMiniMapHeight(int newMiniHeight){
        miniMapHeight = newMiniHeight;
    }
    
    public double GetLocationX(){
        return locationX;
    }
    
    public void SetLocationX(double lX){
        // Trying to move past right edge
        if (lX < 0){
            lX = 0;
        }
        // Trying to move past left edge
        if (lX > logicalWidth-widgetWidth){
            lX = logicalWidth-widgetWidth;
        }
        locationX = lX;
        NotifySubscribers();
    }
    
    public double GetLocationY(){
       return locationY; 
    }
    
    public void SetLocationY(double lY){
        // Trying to move past top
        if (lY < 0){
            lY = 0;
        }
        // Trying to move past bottom
        if (lY > logicalHeight-widgetHeight){
            lY = logicalHeight-widgetHeight;
        }
        locationY = lY;
        NotifySubscribers();
    } 
}
