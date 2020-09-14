package assignment3;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
* HRM184
 */
public class GraphView extends Pane {
    
    // view tools
    Canvas canvas;
    GraphicsContext gc;
    
    // components
    protected GraphModel  model;
    protected InteractionModel interaction;
   
    
    public GraphView(int wHeight, int wWidth){
        canvas = new Canvas(wHeight,wWidth);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);        
        model = null;
        interaction = null;
    }
    
    public void draw(){
        gc.clearRect(0, 0, 500, 500);
        gc.setFill(Color.DARKGREY);
        gc.fillRect(0, 0, 500, 500);
        if(model != null){
            DrawEdges();
            DrawVertexs();
        }
    }
    
    protected void DrawEdges(){
        ArrayList<Edge> edges = model.GetEdgeList();
        double viewX = interaction.GetLocationX();
        double viewY = interaction.GetLocationY();
        
        gc.setFill(Color.BLACK);
        gc.setLineWidth(1f);
        for (int i = 0; i < edges.size(); i += 1){
            Edge tempEdge = edges.get(i);
            gc.strokeLine(tempEdge.GetSX()-viewX, tempEdge.GetSY()-viewY, tempEdge.GetEX()-viewX, tempEdge.GetEY()-viewY);
        }
    }
    
    protected void DrawVertexs(){
        ArrayList<Vertex> circles = model.GetCircleList();
        double viewX = interaction.GetLocationX();
        double viewY = interaction.GetLocationY();
        
        for (int i = 0; i < circles.size(); i += 1){
            Vertex tempVertex = circles.get(i);
            // check to see if this vertex is special 
            if (tempVertex == interaction.GetVertexSelected()){
                if (interaction.GetEdgeSelected() == null){
                    gc.setFill(Color.ORANGE);
                } else {
                    gc.setFill(Color.CYAN);
                    gc.setLineWidth(4f);
                    gc.strokeOval(tempVertex.GetX()-viewX-25, tempVertex.GetY()-viewY-25, 50, 50);
                }
            } else{
                gc.setFill(Color.CYAN);
            }
            gc.fillOval(tempVertex.GetX()-viewX-25, tempVertex.GetY()-viewY-25, 50, 50);
            gc.setFill(Color.BLACK);
            gc.fillText(""+i, tempVertex.GetX()-viewX-5, tempVertex.GetY()-viewY+5);
        }
    }
    
    public void SetModel(GraphModel newModel){
        model = newModel;
    }
    
    public void SetInteractionModel(InteractionModel newInteraction){
        interaction = newInteraction;
    }
    
    public void modelChanged(){
        draw();
    }
}
