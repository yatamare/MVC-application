package assignment3;

/**
 *  HRM184
 */
public class Edge {
    
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    
    private Vertex start;
    private Vertex end;
    
    public Edge(double sX, double sY, double eX, double eY){
        startX = sX;
        startY = sY;
        endX = eX;
        endY = eY;
    }
       
    public boolean Contains(double sX, double sY, double eX, double eY){
        if ((sX == startX) && (sY == startY) && (eX == endX) && (eY ==  endY)){
            return true;
        }
        return false;
    }
    
    public void ModelChanged(){
        startX = start.GetX();
        startY = start.GetY();
        endX = end.GetX();
        endY = end.GetY();
    }
    
    // Set'ers for circle position (encase circles are moved, edge should keep)
    public void SetStartVertex(Vertex newVertex){
        start = newVertex;
    }
    
    public void SetEndVertex(Vertex newVertex){
        end = newVertex;
    }
    
    // Set'ers Get'ers for X & Y Positions
    public void SetStartPoint(double x, double y){
        startX = x;
        startY = y;
    }
    
    public void SetEndPoint(double x, double y){
        endX = x;
        endY = y;
    }
    
    public double GetSX(){
        return startX;
    }
    
    public double GetSY(){
        return startY;
    }
    
    public double GetEX(){
        return endX;
    }
    
    public double GetEY(){
        return endY;
    }
    
    
}
