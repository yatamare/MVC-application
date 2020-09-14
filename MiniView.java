package assignment3;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * HRM184
 */
public class MiniView extends GraphView {
    
    // Value
    private int height;
    private int width;
    
    // 
    private double pvHeight;
    private double pvWidth;
    
   
    public MiniView(int newWidth, int newHeight){
        super(newHeight, newWidth);
        width = newWidth;
        height = newHeight;
  }
  
    @Override
    public void draw(){
        // background
        gc.clearRect(0, 0, width,height);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, width, height);
        FindPixelValue();
        DrawGreenBox();
        DrawVertexs();
        DrawEdges();
  }
    
    private void FindPixelValue(){
        pvHeight = (double)height/interaction.GetLogicalHeight();
        pvWidth = (double)width/interaction.GetLogicalWidth();
    }
  
    private void DrawGreenBox(){
        gc.setFill(Color.LIGHTGREEN);
        double startX = interaction.GetLocationX()*pvWidth;
        double startY = interaction.GetLocationY()*pvHeight;
        double viewX = interaction.GetWidgetWidth()*pvWidth;
        double viewY = interaction.GetWidgetHeight()*pvHeight;
        gc.fillRect(startX, startY, viewX, viewY);    
  }
  
    @Override
    protected void DrawVertexs(){
        ArrayList<Vertex> circles = model.GetCircleList();
        
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.ORANGE);        
        for (int i = 0; i < circles.size(); i += 1){
            Vertex tempVertex = circles.get(i);
            // check to see if this vertex is special 
            if (tempVertex == interaction.GetVertexSelected()){
                gc.fillOval((tempVertex.GetX()-25)*pvWidth, (tempVertex.GetY()-25)*pvHeight, 
                        50*pvWidth, 50*pvHeight);
            } else{
                gc.strokeOval((tempVertex.GetX()-25)*pvWidth, (tempVertex.GetY()-25)*pvHeight, 
                        50*pvWidth, 50*pvHeight);
            }
        }
    }
  
    @Override
    protected void DrawEdges(){
        ArrayList<Edge> edges = model.GetEdgeList();
        
        gc.setFill(Color.BLACK);
        gc.setLineWidth(1f);
        for (int i = 0; i < edges.size(); i += 1){
            Edge tempEdge = edges.get(i);
            gc.strokeLine(tempEdge.GetSX()*pvWidth, tempEdge.GetSY()*pvHeight, 
                    tempEdge.GetEX()*pvWidth, tempEdge.GetEY()*pvWidth);
        }
    }
    
    @Override
    public void SetInteractionModel(InteractionModel newInteraction){
        interaction = newInteraction;
        interaction.SetMiniMapHeight(height);
        interaction.SetMiniMapWidth(width);
    }
}
