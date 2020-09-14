package assignment3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * HRM184
 */
public class GraphDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GraphView view = new GraphView(500,500);
        MiniView miniView = new MiniView(100,100);
        
        GraphViewController controller = new GraphViewController();
        MiniViewController miniController = new MiniViewController();
        
        GraphModel model = new GraphModel();
        InteractionModel interaction = new InteractionModel(500,500,2000,2000);
       
        view.SetModel(model);
        view.SetInteractionModel(interaction);
        miniView.SetModel(model);
        miniView.SetInteractionModel(interaction);
        controller.SetModel(model);
        controller.SetInteractionModel(interaction);
        miniController.SetModel(model);
        miniController.SetInteraction(interaction);
        
        model.AddSubscriber(view);
        model.AddSubscriber(miniView);
        interaction.AddSubscriber(view);
        interaction.AddSubscriber(miniView);
               
        view.setOnMousePressed(controller::handleMouseClick);
        view.setOnMouseDragged(controller::handleMouseDrag);
        view.setOnMouseReleased(controller::handleMouseRelease);
        
        miniView.setOnMousePressed(miniController::handleMousePress);
        miniView.setOnMouseDragged(miniController::handleMouseDrag);
        miniView.setOnMouseReleased(miniController::handleMouseRelease);
        
        StackPane root = new StackPane();
        StackPane.setAlignment(view, Pos.TOP_LEFT);
        StackPane.setAlignment(miniView, Pos.TOP_LEFT);
        root.getChildren().add(view);
        root.getChildren().add(miniView);
        miniView.setPickOnBounds(false);
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Assignment Three");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        view.draw();
        miniView.draw();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
