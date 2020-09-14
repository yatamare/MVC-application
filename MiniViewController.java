package assignment3;

import javafx.scene.input.MouseEvent;

/**
 * HRM184
 */
public class MiniViewController {
    
    // Components
    private GraphModel model;
    private InteractionModel interaction;
    
    // States
    private boolean stateMoving;
    
    // Mouse Info
    private double mouseX;
    private double mouseY;
         
    public MiniViewController(){
        model = null;
        interaction = null;
        stateMoving = false;
    }
    
    public void handleMousePress(MouseEvent event){
        mouseX = event.getX();
        mouseY = event.getY();
        
        // Check if the press was within the green box
        double pvHeight = (double)interaction.GetMiniMapHeight()/interaction.GetLogicalHeight();
        double pvWidth = (double)interaction.GetMiniMapWidth()/interaction.GetLogicalWidth();
        double startX = interaction.GetLocationX()*pvWidth;
        double startY = interaction.GetLocationY()*pvHeight;
        double viewX = interaction.GetWidgetWidth()*pvWidth;
        double viewY = interaction.GetWidgetHeight()*pvHeight;
        if ((mouseX > startX) && (mouseX < startX+viewX)){
            if ((mouseY > startY) && (mouseY < startY+viewY)){
                stateMoving = true;
            }
        }
    }
    
    public void handleMouseDrag(MouseEvent event){
        if (stateMoving == true){
            double bonusSpeedWidth = interaction.GetLogicalWidth()/interaction.GetMiniMapWidth();
            double bonusSpeedHeight = interaction.GetLogicalHeight()/interaction.GetMiniMapHeight();
            if (event.getX() > mouseX){
                    interaction.SetLocationX(interaction.GetLocationX()+(event.getX()-mouseX)*bonusSpeedWidth);
                    mouseX = event.getX();
                } else  if (event.getX() < mouseX) {
                    interaction.SetLocationX(interaction.GetLocationX()-(mouseX-event.getX())*bonusSpeedWidth);
                    mouseX = event.getX();
                }
                if (event.getY() > mouseY) {
                    interaction.SetLocationY(interaction.GetLocationY()+(event.getY()-mouseY)*bonusSpeedHeight);
                    mouseY = event.getY();
                } else if (event.getY() < mouseY){
                    interaction.SetLocationY(interaction.GetLocationY()-(mouseY-event.getY())*bonusSpeedHeight);
                    mouseY = event.getY();
                }
        }
        model.NotifySubscribers();
        interaction.NotifySubscribers();
    }
    
    public void handleMouseRelease(MouseEvent event){
        stateMoving = false;
    }
    
    public void SetModel(GraphModel newModel){
        model = newModel;
    }
    
    public void SetInteraction(InteractionModel newInteraction){
        interaction = newInteraction;
    }
}
