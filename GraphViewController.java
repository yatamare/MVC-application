package assignment3;

import javafx.scene.input.MouseEvent;

/**
 * HRM184
 */
public class GraphViewController {
    
    // components
    private GraphModel model;
    private InteractionModel interaction;
    
    // States
    private int state;
    final private int stateVertexSelected = 0;
    final private int stateEdgeSelected = 1;
    final private int stateUnselected = 2;
    final private int stateMoving = 3;
    
    private double mouseX;
    private double mouseY;
    
    
    public GraphViewController(){
        model = null;
    }
    
    public void SetModel(GraphModel newModel){
        model = newModel;
    }
    
    public void SetInteractionModel(InteractionModel newModel){
        interaction = newModel;
    }
    
    public void handleMouseClick(MouseEvent event){
        double viewX = interaction.GetLocationX();
        double viewY = interaction.GetLocationY();
        mouseX = event.getX();
        mouseY = event.getY();
        Vertex eVertex = model.Contains(mouseX+viewX, mouseY+viewY);
        if (eVertex == null){
            state = stateUnselected;
        } else {
            state = stateVertexSelected;
            interaction.SetVertexSelected(eVertex);
            if (event.isShiftDown()){
                state = stateEdgeSelected;
                interaction.SetEdgeSelected(new Edge(eVertex.GetX(), eVertex.GetY(),
                        mouseX+viewX, mouseY+viewY));
                interaction.GetEdgeSelected().SetStartVertex(eVertex);
                model.AddEdge(interaction.GetEdgeSelected());
            }
        }
    }
    
    public void handleMouseDrag(MouseEvent event){
        double viewX = interaction.GetLocationX();
        double viewY = interaction.GetLocationY();
        switch(state){
            case stateVertexSelected:
                interaction.GetVertexSelected().SetXY(event.getX()+viewX, event.getY()+viewY);
                model.NotifySubscribers();
                model.NotifyEdges();
                break;
            case stateEdgeSelected:
                interaction.GetEdgeSelected().SetEndPoint(event.getX()+viewX, event.getY()+viewY);
                model.NotifySubscribers();
                break;
            case stateUnselected:
                if ((mouseX-10 > event.getX()) || (mouseX+10 < event.getX())){  
                    state = stateMoving;
                }
                else if ((mouseY-10 > event.getY()) || (mouseY+10 < event.getY())){
                    state = stateMoving;
                }
                break;
            case stateMoving:
                if (event.getX() > mouseX){
                    interaction.SetLocationX(interaction.GetLocationX()-(event.getX()-mouseX));
                    mouseX = event.getX();
                } else  if (event.getX() < mouseX) {
                    interaction.SetLocationX(interaction.GetLocationX()+(mouseX-event.getX()));
                    mouseX = event.getX();
                }
                if (event.getY() > mouseY) {
                    interaction.SetLocationY(interaction.GetLocationY()-(event.getY()-mouseY));
                    mouseY = event.getY();
                } else if (event.getY() < mouseY){
                    interaction.SetLocationY(interaction.GetLocationY()+(mouseY-event.getY()));
                    mouseY = event.getY();
                }
                break;
        }
    }
    
    public void handleMouseRelease(MouseEvent event){
        double viewX = interaction.GetLocationX();
        double viewY = interaction.GetLocationY();
        switch(state){
            case stateEdgeSelected:
                Vertex eVertex = model.Contains(event.getX()+viewX, event.getY()+viewY);
                if (eVertex == null){
                    model.RemoveEdge(interaction.GetEdgeSelected());
                } else {
                    interaction.GetEdgeSelected().SetEndPoint(eVertex.GetX(), eVertex.GetY());
                    interaction.GetEdgeSelected().SetEndVertex(eVertex);
                }
                break;
            case stateUnselected:
                model.AddCircle(new Vertex(event.getX()+viewX, event.getY()+viewY));
                break;
        }
        interaction.SetEdgeSelected(null);
        interaction.SetVertexSelected(null);
        model.NotifySubscribers();
    }
}
