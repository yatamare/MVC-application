package assignment3;

/**
 * HRM184
 */
public class Vertex {
    
    private double xPosition;
    private double yPosition;
       
    public Vertex(double x, double y){
        xPosition = x;
        yPosition = y;
    }
       
    public double GetX(){
        return xPosition;
    }
       
    public double GetY(){
        return yPosition;
    }
       
    public void SetXY(double x, double y){
        xPosition = x;
        yPosition = y;
    }
       
    public boolean Contains(double x, double y){
        if ((x > xPosition-25) && (x < xPosition+25)){
            if ((y > yPosition-25) && (y < yPosition+25)){
                return true;
            }
        }
        return false;
    }
}
